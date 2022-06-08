package com.spring.board.board.controller;

import com.spring.board.board.service.BoardService;
import com.spring.board.board.vo.ArticleVO;
import com.spring.board.paging.PageMaker;
import com.spring.board.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller("boardController")
public class BoardControllerImpl  implements BoardController{
//	@Override
//	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		return null;
//	}

	private final BoardService boardService;
	private final PageMaker pageMaker;

	@Autowired
	public BoardControllerImpl(BoardService boardService, PageMaker pageMaker) {
		this.boardService = boardService;
		this.pageMaker = pageMaker;
	}
//	@Autowired
//	public BoardControllerImpl(BoardService boardService) {
//		this.boardService = boardService;
//	}

	@Override
	@RequestMapping(value= "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listArticles(@RequestParam(required = false,defaultValue = "1") int page,
									 @RequestParam(required = false,defaultValue = "10") int listSize,
									 HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("리스트 호출");

		int boardAllCount = boardService.getBoardAllCount();

//		Pagination pagination = new Pagination(page,range ,listSize, boardAllCount);
//
//		System.out.println("start = " + pagination.getStartList());
//		System.out.println("end = " + pagination.getEndList());
//		System.out.println("count = " + pagination.getPageCount());
//		System.out.println("listCount = " + pagination.getListCount());
		Pagination pagination = pageMaker.pageSort(page ,listSize, boardAllCount);
		ModelAndView mav = new ModelAndView();

		mav.addObject("pagination", pagination);
		List<ArticleVO> articlesList = boardService.listArticles(pagination);
		mav.addObject("articlesList", articlesList);
		mav.setViewName("listArticles");
		return mav;
	}

	@Override
	@RequestMapping(value="/board/addNewArticle.do" ,method = {RequestMethod.GET, RequestMethod.POST})
	public String addNewArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("id", request.getParameter("id"));
		articleMap.put("title", request.getParameter("title"));
		articleMap.put("content", request.getParameter("content"));
		boardService.addNewArticle(articleMap);

		return "redirect:/board/listArticles.do";
	}

	@Override
	@RequestMapping(value="/board/removeArticle.do" ,method = RequestMethod.POST)
	public String removeArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception {

		boardService.removeArticle(articleNO);
		String gogo = request.getParameter("gogogo");
		Enumeration params = request.getParameterNames();
		System.out.println("----------------------------");
		while (params.hasMoreElements()){
			String name = (String)params.nextElement();
			System.out.println(name + " : " +request.getParameter(name));
		}
		System.out.println("----------------------------");
		return "redirect:" + gogo;
	}
//	@Override
//	@RequestMapping(value="/board/removeArticle.do" ,method = RequestMethod.POST)
//	public ModelAndView removeArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		boardService.removeArticle(articleNO);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("redirect:/board/listArticles.do");
//
//		return mav;
//	}

	@Override
	@RequestMapping(value="/board/viewArticle.do" ,method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,
									HttpServletRequest request, HttpServletResponse response) throws Exception{
//		Map articleMap = boardService.viewArticle(articleNO);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("viewArticle");
//		mav.addObject("articleMap", articleMap);
		return viewBoard(articleNO);
	}

	@Override
	@RequestMapping(value="/board/modArticle.do" ,method = RequestMethod.POST)
	public ModelAndView modArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String articleNo = request.getParameter("articleNO");

		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("title", request.getParameter("title"));
		articleMap.put("content", request.getParameter("content"));
		articleMap.put("articleNO", articleNo);

		boardService.modArticle(articleMap);

		int boardNO= Integer.parseInt(articleNo);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("viewArticle");
//		Map viewBorad=boardService.viewArticle(boardNO);
//		mav.addObject("articleMap", viewBorad);
		return viewBoard(boardNO);
	}


	@RequestMapping(value = "/board/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("articleForm");
		return mav;
	}

	private ModelAndView getBoardList(Pagination pagination) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<ArticleVO> articlesList = boardService.listArticles(pagination);
		mav.addObject("articlesList", articlesList);
		mav.setViewName("listArticles");
		return mav;
	}
	private ModelAndView viewBoard(int articleNO) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewArticle");
		Map articleMap=boardService.viewArticle(articleNO);
		mav.addObject("articleMap", articleMap);
		return mav;
	}

}
