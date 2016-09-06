package controller;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.BookDAO;
import bean.BookDO;

public class BookRegisterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String filename= "";
		//웹 애플리케이션상의 절대 경로
		String realFolder = "";
		//파일이 업로드될 폴더 지정
		String saveFolder ="/bookImage";
		//인코딩 타입
		String encType = "utf-8";
		//최대 업로드될 크기 1MB
		int maxSize = 1*1024*1024;
		//파일 업로드를 수행할 MultipartRequest 객체
		MultipartRequest imageUp = null;
		//웹 애플리케이션상의 절대 경로
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		try{
			//파일 업로드 수행
			imageUp = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			
			//<input type="file">인 모든 파라미터를 얻어냄
			Enumeration<?> files = imageUp.getFileNames();
			
			//파일정보가 있다면
			while(files.hasMoreElements()){
				//input태그의 속성이 file인 태그의 name 속성값을 files.nextElement()가 반환한다.
				String name = (String)files.nextElement();
				//서버에 저장된 파일 이름
				filename = imageUp.getFilesystemName(name);
			}
		} catch(Exception e){
			e.printStackTrace();
		}//end try
		
		//폼으로부터 넘어온 정보중 파일이 아닌 정보를 얻어냄
		BookDO book = new BookDO();
		String book_kind = imageUp.getParameter("book_kind");
		String book_title = imageUp.getParameter("book_title");
		String book_price = imageUp.getParameter("book_price");
		String book_count = imageUp.getParameter("book_count");
		String author = imageUp.getParameter("author");
		String publishing_com = imageUp.getParameter("publishing_com");
		String book_content = imageUp.getParameter("book_content");
		String discount_rate = imageUp.getParameter("discount_rate");
		
		//책 등록일 계산
		String year = imageUp.getParameter("publishing_year");
		String month = imageUp.getParameter("publishing_month").length() == 1 ?
				"0" + imageUp.getParameter("publishing_month") : imageUp.getParameter("publishing_month");
		String day = imageUp.getParameter("publishing_day").length() == 1 ?
				"0" + imageUp.getParameter("publishing_day") : imageUp.getParameter("publishig_day");
		
		book.setBook_kind(book_kind);
		book.setBook_title(book_title);
		book.setBook_price(Integer.parseInt(book_price));
		book.setBook_count(Short.parseShort(book_count));
		book.setAuthor(author);
		book.setPublishing_com(publishing_com);
		book.setPublishing_date(year+"-"+month+"-"+day);
		book.setBook_image(filename);
		book.setBook_content(book_content);
		book.setDiscount_rate(Byte.parseByte(discount_rate));
		book.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		BookDAO bookDao = new BookDAO();
		bookDao.insertBook(book);
		
		request.setAttribute("book_kind", book_kind);
		return "/mngr/productProcess/bookRegisterPro.jsp";
	}

}