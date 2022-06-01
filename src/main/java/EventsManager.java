import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DAO.EventsDAO;
import Service.EventService;
import Service.Impl.EventServiceImpl;
import Bean.Event;
import Bean.EventBean;

@WebServlet("/EventsManager")
public class EventsManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventService eventService = new EventServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			// get connection to DAO
			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FindJobDB");
			EventsDAO eventDAO = new EventsDAO(ds.getConnection());

			if (request.getParameter("UpdateId") != null) { // get UpdateId & deliver event to update page
				EventBean eventForUpdate = eventService.getEvent(Integer.parseInt(request.getParameter("UpdateId")));
				
				if (eventForUpdate == null) {
					getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
				}
				request.setAttribute("eventForUpdate", eventForUpdate);
				getServletContext().getRequestDispatcher("/EventUpdate.jsp").forward(request, response);
			} else if (request.getParameter("DeleteId") != null) { // DeleteId exist & do delete
				processDelete(request, response, eventDAO);
			} else { // Event's index
				showData(request, response);
			}
		} catch (NamingException | SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			// get connection to DAO
//			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FindJobDB");
//			EventsDAO eventDAO = new EventsDAO(ds.getConnection());

			// get upload stream
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(4194304);

			// get fields
			// {[FieldName1="fieldValue1"],[FieldName2="fieldValue2"],[FieldName3="fieldValue3"]}
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> fieldsIterator = fields.iterator();

			// put data into bean
			EventBean event = new EventBean();
			String OringinImgURL = null;
			while (fieldsIterator.hasNext()) {
				FileItem fieldItem = (FileItem) fieldsIterator.next();
				String fieldName = fieldItem.getFieldName();
				String fieldValue = fieldItem.getString("UTF-8");

				if (!fieldItem.isFormField() && !fieldItem.getName().equals("")) {
					// add time stamp to ImgFileName
					File imageFile = new File(System.currentTimeMillis() + "_" + fieldItem.getName());
					String uploadPath = getServletContext().getRealPath(File.separator + "eventsImg");
					File savedFile = new File(uploadPath, imageFile.getName());
					// save Img file in absolute path
					fieldItem.write(savedFile);
					// set ImgURL in relative path
					event.setImgUrl("eventsImg" + File.separator + savedFile.getName());
				} else if (fieldName.equals("adId")) {
					event.setEventId(Integer.parseInt(fieldValue));
				} else if (fieldName.equals("compId")) {
					event.setCompId(fieldValue);
				} else if (fieldName.equals("price")) {
					event.setPrice(Integer.parseInt(fieldValue));
				} else if (fieldName.equals("eventLinkURL")) {
					event.setEventLinkUrl(fieldValue);
				} else if (fieldName.equals("postStart")) {
					event.setPostStart(stringToSQLDate(fieldValue));
				} else if (fieldName.equals("postEnd")) {
					event.setPostEnd(stringToSQLDate(fieldValue));
				} else if (fieldName.equals("remark")) {
					event.setRemark(fieldValue);
				} else if (fieldName.equals("OringinImgURL")) {
					OringinImgURL = fieldValue;
				}
			}
			if (event.getImgUrl() == null) { // if img did not upload set OringinImgURL
				event.setImgUrl(OringinImgURL);
			}

			if (event.getEventId() == 0) { // EventCreate.jsp <input:hidden name="adId" value="0">
				processCreate(request, response, event);
			} else {
				processUpdate(request, response, event);
			}

		} catch (NamingException | SQLException | ParseException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showData(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<EventBean> events = eventService.getAllEvents();

		request.setAttribute("events", events);
		getServletContext().getRequestDispatcher("/EventsDashBoard.jsp").forward(request, response);

	}

	private void processDelete(HttpServletRequest request, HttpServletResponse response, EventsDAO eventDAO)
			throws SQLException, IOException, ParseException, ServletException {
		int deleteId = Integer.parseInt(request.getParameter("DeleteId"));

		if (eventDAO.deleteEvent(deleteId)) {
			System.out.println("DeleteId:" + deleteId + " Delete success");
			response.sendRedirect("./EventsManager");
		} else {
			System.out.println("DeleteId:" + deleteId + " Delete fail");
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
		eventDAO.closeConn();
	}

	private void processCreate(HttpServletRequest request, HttpServletResponse response, EventBean event)
			throws SQLException, IOException, ParseException, ServletException {
		int n = eventService.save(event);
		if (n > 0) {
			System.out.println("Create success");
			response.sendRedirect("./EventsManager");
		} else {
			System.out.println("Create fail");
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}

	private void processUpdate(HttpServletRequest request, HttpServletResponse response, EventBean event)
			throws SQLException, IOException, ParseException, ServletException {
		eventService.updateEvent(event);
		System.out.println("UpdateId:" + event.getEventId() + " Update success");
		response.sendRedirect("./EventsManager");
	}

	public void init() throws ServletException {
		File uploadDir = new File(getServletContext().getRealPath(File.separator + "eventsImg"));
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
	}

	private Date stringToSQLDate(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date utilDate = df.parse(date);
		return utilDate;
	}

}
