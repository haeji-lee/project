package com.springbook.view.board;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
@SessionAttributes("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	// 글 등록
	@RequestMapping(value = "/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo) throws Exception{
		//System.out.println("version2 디버깅 ::: " + file);
		MultipartFile uploadFile = vo.getUploadFile();
		System.out.println("디버깅 ::: " +uploadFile);
		if(!uploadFile.isEmpty()) {
			String fileName=uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("/Users/haejilee/" + fileName));
		}
		boardService.insertBoard(vo);
		return "getBoardList.do";
	}

	// 글 수정
	@RequestMapping(value="/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println(" 작성자 이름 : " + vo.getWriter());
		boardService.updateBoard(vo);
		return "getBoardList.do";
	}

	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "getBoardList.do";
	}

	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		model.addAttribute("board", boardService.getBoard(vo)); // Model 정보 저장
	//	mav.setViewName("getBoard.jsp"); // View 정보 저장
		return "getBoard.jsp";
	}


//    public String getBoaard(@RequestParam(value="searchCondition", defaultValue="TITLE", required=false) String condition,
//    		@RequestParam(value="searchKeyword", defaultValue="", required=false)String keyword
//    		, BoardDAO boardDAO, Model model)	{
//		System.out.println(" 검색 조건 : " + condition);
//		System.out.println(" 검색 단어 : " + keyword);
//		
//		model.addAttribute("boardList", boardDAO.getBoardList(vo));
//		return "getBoardList.jsp";
//	}
	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		
		System.out.println(vo.getSearchCondition());
		System.out.println(vo.getSearchKeyword());
		
		
		// NULL Check
		if(vo.getSearchCondition()==null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword()== null) vo.setSearchKeyword("");
		
		model.addAttribute("boardList", boardService.getBoardList(vo)); // Model 정보 저장
		return "getBoardList.jsp";
	}
}
