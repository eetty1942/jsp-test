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
		
		// 앞서 테스트 케이스에 작성된 내용때문에 인덱싱 넘버 증가로 삭제 로직 추가
		dao.delete("1");
		
		BoardDTO dto = new BoardDTO();
		dto.setAuthor("홍길동");
		dto.setTitle("테스트입니다");
		dto.setContent("내용내용");
		dto.setReadcnt(0);	

		dao.write(dto);
		
		//When
		BoardDTO actual = dao.retrieve("1");
		

		//Then
		assertNotNull(actual);		// 불러온 내용이 Null이 아닌지 확인
		assertEquals(1, actual.getReadcnt());	// 조회수 증가 확인
		assertEquals(dto, actual);	//정상적으로 불러왔는지 확인
	}
	
	
	
	@Test
	public void testUpdate() throws Exception {
		//Given
		BoardDTO dto = new BoardDTO();
		dto.setNum(1);
		dto.setAuthor("홍길동");
		dto.setTitle("테스트입니다");
		dto.setContent("내용내용");
		
		dao.write(dto);
		
		//When
		dto.setTitle("수정했어여");
		String title = dto.getTitle();
		int resultData = dao.update(dto);
		
		//Then
		assertEquals(1, resultData);				//수정 결과 성공인지 확인
		assertEquals(title, dto.getTitle()); 		//제목만 수정하였고 이 내용 적용되었는지 확인
	}
	
	@Test
	public void testDelete() throws Exception {

		//Given
		BoardDTO dto = new BoardDTO();
		dto.setNum(1);
		dto.setAuthor("홍길동");
		dto.setTitle("테스트입니다");
		dto.setContent("내용내용");
		
		dao.write(dto);

		//When
		int actual = dao.delete("1");

		//Then
		assertEquals(1, actual);		// 삭제 과정을 수행하면 1을 리턴하므로 이 결과가 맞는지 확인한다.
	}
}
