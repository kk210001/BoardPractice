package com.spring.board.board.controller;

import com.spring.board.board.service.BoardService;
import com.spring.board.board.vo.ArticleVO;
import com.spring.board.paging.PageMaker;
import com.spring.board.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller("boardController")
@RequestMapping("/board")
public class BoardControllerImpl  implements BoardController{

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
	@RequestMapping(value = "/listArticles.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String listArticles(@RequestParam(required = false,defaultValue = "1") int page,
									 @RequestParam(required = false,defaultValue = "10") int listSize,
									 Model model) throws Exception {
		System.out.println("리스트 호출");

		int boardAllCount = boardService.getBoardAllCount();

//		Pagination pagination = new Pagination(page,range ,listSize, boardAllCount);
//
//		System.out.println("start = " + pagination.getStartList());
//		System.out.println("end = " + pagination.getEndList());
//		System.out.println("count = " + pagination.getPageCount());
//		System.out.println("listCount = " + pagination.getListCount());
		Pagination pagination = pageMaker.pageSort(page ,listSize, boardAllCount);

		model.addAttribute("pagination", pagination);
		List<ArticleVO> articlesList = boardService.listArticles(pagination);
		model.addAttribute("articlesList", articlesList);
		return "listArticles";
	}

	@Override
	@PostMapping("/addNewArticle.do")
	public String addNewArticle(@ModelAttribute("article") ArticleVO articleVO) throws Exception {

		Map<String, String> articleMap = setArticleMap(articleVO);
		boardService.addNewArticle(articleMap);

		return "redirect:/board/listArticles.do";
	}

	//add Article
	private Map<String, String> setArticleMap(ArticleVO articleVO) {
		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("id", articleVO.getId());
		articleMap.put("title", articleVO.getTitle());
		articleMap.put("content", articleVO.getContent());
		return articleMap;
	}

	@Override
	@PostMapping("/removeArticle.do")
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
	@RequestMapping(value = "/viewArticle.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String viewArticle(@RequestParam("articleNO") int articleNO,
									Model model) throws Exception{
		return viewBoard(articleNO, model);
	}

	@Override
	@PostMapping("/modArticle.do")
	public String modArticle(@ModelAttribute("article") ArticleVO articleVO,Model model) throws Exception {

		Map<String, String> articleMap = getArticleMap(articleVO);
		boardService.modArticle(articleMap);

		int articleNo = Integer.parseInt(articleMap.get("articleNO"));
		System.out.println("articleNo = " + articleNo);

		return viewBoard(articleNo, model);
	}

	//mod Article
	private Map<String, String> getArticleMap(ArticleVO articleVO) {
		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("title", articleVO.getTitle());
		articleMap.put("content", articleVO.getContent());
		articleMap.put("articleNO", articleVO.getArticleNO() + "");
		return articleMap;
	}


	@RequestMapping(value = "/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("articleForm");
		return mav;
	}

	private String viewBoard(int articleNO, Model model) throws Exception {

		Map articleMap=boardService.viewArticle(articleNO);
		model.addAttribute("articleMap", articleMap);
		return "viewArticle";
	}

}
