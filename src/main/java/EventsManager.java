import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import Service.EventService;
import Service.Impl.EventServiceImpl;
import Bean.EventBean;

@WebServlet("/EventsManager")
public class EventsManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("UpdateId") != null) {
				EventService eventService = new EventServiceImpl();
				EventBean eventForUpdate = eventService.getEvent(Integer.parseInt(request.getParameter("UpdateId")));
				if (eventForUpdate == null) {
					request.getRequestDispatcher("404.jsp").forward(request, response);
				}
				request.setAttribute("eventForUpdate", eventForUpdate);
				request.getRequestDispatcher("EventUpdate.jsp").forward(request, response);
			} else if (request.getParameter("DeleteId") != null) {
				int deleteId = Integer.parseInt(request.getParameter("DeleteId"));
				processDelete(request, response, deleteId);
			} else {
				showData(request, response);
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(4194304);

			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> fieldsIterator = fields.iterator();

			EventBean event = new EventBean();
			String OringinImgURL = null;
			while (fieldsIterator.hasNext()) {
				FileItem fieldItem = (FileItem) fieldsIterator.next();
				String fieldName = fieldItem.getFieldName();
				String fieldValue = fieldItem.getString("UTF-8");

				if (!fieldItem.isFormField() && !fieldItem.getName().equals("")) {
					File imageFile = new File(System.currentTimeMillis() + "_" + fieldItem.getName());
					String uploadPath = getServletContext().getRealPath(File.separator + "eventsImg");
					File savedFile = new File(uploadPath, imageFile.getName());
					fieldItem.write(savedFile);
					event.setImgUrl("eventsImg" + File.separator + savedFile.getName());
				} else if (fieldName.equals("eventId")) {
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
			if (event.getImgUrl() == null) {
				event.setImgUrl(OringinImgURL);
			}

			if (event.getEventId() == 0) {
				processCreate(request, response, event);
			} else {
				processUpdate(request, response, event);
			}

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showData(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		EventService eventService = new EventServiceImpl();
		List<EventBean> events = eventService.getAllEvents();
		request.setAttribute("events", events);
		request.getRequestDispatcher("EventsDashBoard.jsp").forward(request, response);
	}

	private void processDelete(HttpServletRequest request, HttpServletResponse response, int deleteId)
			throws SQLException, IOException, ParseException, ServletException {
		EventService eventService = new EventServiceImpl();
		eventService.deleteEvent(deleteId);
		response.sendRedirect("./EventsManager");
	}

	private void processCreate(HttpServletRequest request, HttpServletResponse response, EventBean event)
			throws SQLException, IOException, ParseException, ServletException {
		EventService eventService = new EventServiceImpl();
		eventService.save(event);
		response.sendRedirect("./EventsManager");
	}

	private void processUpdate(HttpServletRequest request, HttpServletResponse response, EventBean event)
			throws SQLException, IOException, ParseException, ServletException {
		EventService eventService = new EventServiceImpl();
		eventService.updateEvent(event);
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
