package tw.team5.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tw.team5.bean.Member;
import tw.team5.service.MemberService;
import tw.team5.service.impl.MemberServiceImpl;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 獲取小量資料可用doGet，但需留意隱蔽性問題
		request.setCharacterEncoding("UTF-8");
		try {

			// 如果dashboard傳回的updateid有值，先查出該id的所有內容，放進updatemember物件
			if (request.getParameter("UpdateId") != null) {
				
				MemberService memberService = new MemberServiceImpl();
				Member UpdateMember = memberService.getMember(Integer.parseInt(request.getParameter("UpdateId")));
				
				if (UpdateMember == null) {
					getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
				}
				request.setAttribute("UpdateMember", UpdateMember); // 把key跟value放進request中，並跳轉至update頁面
				getServletContext().getRequestDispatcher("/MemberUpdate.jsp").forward(request, response);
			} else if (request.getParameter("DeleteId") != null) {
				int deleteId = Integer.parseInt(request.getParameter("DeleteId"));
				processDelete(request, response, deleteId);
			} else { // 如果dashboard沒傳回updateid或是deleteid的話，直接呈現所有query
				showData(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 填入較多資料用doPost
		try {

			String update123 = null;
			// 建立上傳檔案的factory、以及可裝upload資訊的物件
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(4194304);
			
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> fieldsIterator = fields.iterator();

			// put data into bean
			Member member = new Member();
			String OringinImgURL = null;
			while (fieldsIterator.hasNext()) {
				FileItem fieldItem = (FileItem) fieldsIterator.next();
				String fieldName = fieldItem.getFieldName();
				String fieldValue = fieldItem.getString("UTF-8");
				//判斷整包裡面是否有圖片
				if (!fieldItem.isFormField() && !fieldItem.getName().equals("")) {
					// 幫檔名前面自動加上毫秒_，使檔名永遠不重複
					File imageFile = new File(System.currentTimeMillis() + "_" + fieldItem.getName());
					//File.separator可使不同作業系統的路徑正確
					String uploadPath = getServletContext().getRealPath(File.separator + "membersImg");
					File savedFile = new File(uploadPath, imageFile.getName());
					//絕對路徑
					fieldItem.write(savedFile);
					//相對路徑，如果沒放圖片資料庫路徑就會顯示NULL
					member.setImage("membersImg" + File.separator + savedFile.getName());

				//判斷其他資料內容，不是圖片的話就把值set進去member中，取值都在這邊做	
				} else if (fieldName.equals("idNumber")) {
					member.setIdNumber(Integer.parseInt(fieldValue));
				} else if (fieldName.equals("userid")) {
					member.setUserid(fieldValue);
				} else if (fieldName.equals("pwd")) {
					member.setPwd(fieldValue);
				} else if (fieldName.equals("username")) {
					member.setUsername(fieldValue);
				} else if (fieldName.equals("gender")) {
					member.setGender(fieldValue);
				} else if (fieldName.equals("birth")) {
					member.setBirth(Member.strToDate(fieldValue));
				} else if (fieldName.equals("tele")) {
					member.setTele(fieldValue);
				} else if (fieldName.equals("phone")) {
					member.setPhone(fieldValue);
				} else if (fieldName.equals("address")) {
					member.setAddress(fieldValue);
				} else if (fieldName.equals("email")) {
					member.setEmail(fieldValue);
				} else if (fieldName.equals("point")) {
					member.setPoint(Integer.parseInt(fieldValue));
				} else if (fieldName.equals("OringinImgURL")) {
					OringinImgURL = fieldValue;
				}else if (fieldName.equals("update123")) {
					update123 = fieldValue;
				}
			}
			if (member.getImage() == null) { // 如果沒有上傳照片，就使用預設圖片
				member.setImage(OringinImgURL);
			}
			
			if (update123 != null) {
				processUpdate(request, response, member);
			}
			else{
				processCreate(request, response, member);
			}
			

		} catch ( SQLException | ParseException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showData(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		
		
		MemberService memberService = new MemberServiceImpl();
		List<Member> members = memberService.getAllMembers();
		System.out.println(members);
		request.setAttribute("members", members);
		getServletContext().getRequestDispatcher("/MemberDashBoard.jsp").forward(request, response);

//		if (members != null) {
//			request.setAttribute("members", members); // 搜尋所有的資料，把key跟value放進request
//		} else {
//			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
//		}
	}

	
	// CREATE
	private void processCreate(HttpServletRequest request, HttpServletResponse response, Member member)
			throws SQLException, IOException, ParseException, ServletException {
	
		MemberService memberService = new MemberServiceImpl();
		memberService.save(member);
		response.sendRedirect("./MemberServlet");
		
		
		
	/*	原版本有controllor驗證，需要重寫
		boolean CheckId= memberDAO.findUserid(member.getUserid());
		if (memberDAO.createMember(member)) {
			response.sendRedirect("./MemberServlet");
		}else if(CheckId){
			System.out.println("重複的身分證字號:" + member.getUserid() );
			String message = "身分證字號不可與他人重複";
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/MemberCreate.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
	*/	
		
	}

	// Update
	private void processUpdate(HttpServletRequest request, HttpServletResponse response, Member member)
			throws SQLException, IOException, ServletException {
	
		MemberService memberService = new MemberServiceImpl();
		memberService.updateMember(member);
		response.sendRedirect("./MemberServlet");
		
	/*	
		if (memberDAO.updateMember(member)) { 
			response.sendRedirect("./MemberServlet");
		} else {
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
	*/
		
	}

	// DELETE
	private void processDelete(HttpServletRequest request, HttpServletResponse response, int deleteId)
			throws SQLException, IOException {

		
		MemberService memberService = new MemberServiceImpl();
		memberService.deleteMember(deleteId);
		response.sendRedirect("./MemberServlet");
		
	}

	public void init() throws ServletException {
		File uploadDir = new File(getServletContext().getRealPath(File.separator + "membersImg"));
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
	}
	
	
}
