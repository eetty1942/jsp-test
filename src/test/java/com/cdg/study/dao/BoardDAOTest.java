package com.cdg.study.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cdg.study.entity.BoardDTO;



public class BoardDAOTest {

	private BoardDAO dao;
	
	@Before
	public void setUp() throws Exception {
		dao = BoardDAO.getInstance();
	}
	
	@Test
	public void testGetList() throws Exception {
		//When
		List<BoardDTO> actual = dao.getList();
		
		//Then
		assertNotNull(actual);
	}
	
	@Test
	public void testWrite() throws Exception {
		//Given
		BoardDTO dto = new BoardDTO();
		dto.setAuthor("홍길동");
		dto.setTitle("테스트입니다");
		dto.setContent("내용내용");

		//When
		int actual = dao.write(dto);

		//Then
		assertEquals(1, actual);
	}
	
	@Test
	public void testRetrieve() throws Exception {
		//Given
		int num = 1;
		BoardDTO dto = new BoardDTO();
		dto.setNum(num);
		dto.setAuthor("홍길동");
		dto.setTitle("테스트입니다");
		dto.setContent("내용내용");
		dto.setReadcnt(0);

		//When
		String numDetail = Integer.toString(dto.getNum());
		BoardDTO actual = dao.retrieve(numDetail);
		

		//Then
		assertNotNull(actual);

		assertEquals(1, actual.getReadcnt());
		//assertEquals(dto, actual);
	}
	
	
	
	@Test
	public void testUpdate() throws Exception {
		//Given
		BoardDTO dto = new BoardDTO();
		dto.setNum(1);
		dto.setAuthor("홍길동");
		dto.setTitle("테스트입니다");
		dto.setContent("내용내용");
		int testData = dao.write(dto);
		
		//When
		dto.setTitle("수정했어여");
		String title = dto.getTitle();
		int resultData = dao.update(dto);
		
		//Then
		assertEquals(1, resultData);
		assertEquals(title, dto.getTitle());
	}
	
	@Test
	public void testDelete() throws Exception {
		//Given
		BoardDTO dto = new BoardDTO();
		dto.setNum(1);
		dto.setAuthor("홍길동");
		dto.setTitle("테스트입니다");
		dto.setContent("내용내용");

		//When
		//BoardDTO actual = dao.retrieve("0");
		dto = dao.delete("1");

		//Then
		assertEquals(1, result);
		assertNull(dto);
		//assertEquals(dto, actual);
	}
}
