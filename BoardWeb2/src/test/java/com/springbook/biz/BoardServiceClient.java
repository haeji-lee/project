package com.springbook.biz;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

public class BoardServiceClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1. Spring �����̳ʸ� ����
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. Spring �����̳ʷκ��� BoardServiceImpl��ü�� ����Ѵ�.
		BoardService boardService = (BoardService)container.getBean("boardService");
		
		//3.�� ��� ��� �׽�Ʈ
		BoardVO vo = new BoardVO();
		vo.setTitle("�ӽ� ����");
		vo.setWriter("ȫ�浿");
		vo.setContent("����..");
		boardService.insertBoard(vo);
		
		//4. �۸�� �˻���� �׽�Ʈ
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for(BoardVO board : boardList){
			System.out.println("--------> " + board.toString());			
		}
		
		container.close();
		
	}

}
