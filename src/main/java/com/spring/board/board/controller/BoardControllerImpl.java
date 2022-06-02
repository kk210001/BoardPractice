package com.spring.board.board.controller;

import com.spring.board.board.service.BoardService;
import com.spring.board.board.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller("boardController")
public class BoardControllerImpl  implements BoardController{
	private final BoardService boardService;

	@Autowired
	public BoardControllerImpl(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	@RequestMapping(value= "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("리스트 호출");
		return getBoardList();
	}
	@RequestMapping(value="/board/viewArticle.do" ,method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,
									HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		Map articleMap=boardService.viewArticle(articleNO);
		ModelAndView mav = new ModelAndView();
		System.out.println("articleNO = " + articleNO);
//		mav.setViewName(viewName);
		mav.setViewName("viewArticle");
		mav.addObject("articleMap", articleMap);
		return mav;
	}

	@Override
	@RequestMapping(value="/board/addNewArticle.do" ,method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addNewArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("id", request.getParameter("id"));
		articleMap.put("title", request.getParameter("title"));
		articleMap.put("content", request.getParameter("content"));
		boardService.addNewArticle(articleMap);
		return getBoardList();
	}

	private ModelAndView getBoardList() throws Exception {
		ModelAndView mav = new ModelAndView();
		List<ArticleVO> articlesList = boardService.listArticles();
		mav.addObject("articlesList", articlesList);
		mav.setViewName("listArticles");
		for (ArticleVO o : articlesList) {
			System.out.println("o.get = " + o.getTitle());
		}
		return mav;
	}


//	@Override
//	@RequestMapping(value="/board/addNewArticle.do" ,method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,
//	HttpServletResponse response) throws Exception {
//		multipartRequest.setCharacterEncoding("utf-8");
//		Map<String,Object> articleMap = new HashMap<String, Object>();
//		Enumeration enu=multipartRequest.getParameterNames();
//		while(enu.hasMoreElements()){
//			String name=(String)enu.nextElement();
//			String value=multipartRequest.getParameter(name);
//			articleMap.put(name,value);
//		}
//
//		HttpSession session = multipartRequest.getSession();
//		String id = "1";
//		articleMap.put("parentNO", 0);
//		articleMap.put("id", id);
//
//		String message;
//		ResponseEntity resEnt=null;
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
//		try {
//			int articleNO = boardService.addNewArticle(articleMap);
//
//			message = "<script>";
//			message += " alert('새글을 추가했습니다.');";
//			message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticles.do'; ";
//			message +=" </script>";
//			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//		}catch(Exception e) {
//
//			message = " <script>";
//			message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
//			message +=" location.href='"+multipartRequest.getContextPath()+"/board/articleForm.do'; ";
//			message +=" </script>";
//			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//			e.printStackTrace();
//		}
//		return resEnt;
//	}
//
//
//  @RequestMapping(value="/board/modArticle.do" ,method = RequestMethod.POST)
//  @ResponseBody
//  public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,
//    HttpServletResponse response) throws Exception{
//	Map<String,Object> articleMap = new HashMap<String, Object>();
//
//	String articleNO=(String)articleMap.get("articleNO");
//	String message;
//	ResponseEntity resEnt=null;
//	HttpHeaders responseHeaders = new HttpHeaders();
//	responseHeaders.add("Content-Type", "text/html; charset=utf-8");
//    try {
//       boardService.modArticle(articleMap);
//
//       message = "<script>";
//	   message +=" </script>";
//       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//    }catch(Exception e) {
//      message = "<script>";
//	  message +=" </script>";
//      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//    }
//    return resEnt;
//  }
  
  @Override
  @RequestMapping(value="/board/removeArticle.do" ,method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity  removeArticle(@RequestParam("articleNO") int articleNO,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
	response.setContentType("text/html; charset=UTF-8");
	String message;
	ResponseEntity resEnt=null;
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	try {
		boardService.removeArticle(articleNO);

		message = "<script>";
		message +=" </script>";
	    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	       
	}catch(Exception e) {
		message = "<script>";
		message +=" </script>";
	    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    e.printStackTrace();
	}
	return resEnt;
  }

	@RequestMapping(value = "/board/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
//		mav.setViewName(viewName);
		mav.setViewName("articleForm");
		return mav;
	}

	@RequestMapping(value = "/aaa", method = RequestMethod.GET)
	public String home(Model model) {
		System.out.println("home controller start");

		model.addAttribute("name", "Controller");


		return "index";
	}

}
