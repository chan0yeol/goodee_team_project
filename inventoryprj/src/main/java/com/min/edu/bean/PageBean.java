package com.min.edu.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
	private int page; // 현재 페이지의 번호 0
	private int countList; // 현재페이지당 몇개(row)의 게시글을 보여줄지
	private int totalCount; // 전체 글의 갯수

	private int countPage; // 화면에 몇개의 페이지 그룹
	private int totalPage; // 페이지당 보여줄 게시글의 갯수
	private int stagePage; // 현재 그룹의 시작 번호
	private int endPage; // 현재 그룹의 마지막 번호 페이지번호
	 
	
	public void setTotalPage() {
        int totalPageRes = totalCount / countList;
        if (totalCount % countList > 0) {
            totalPageRes++;  // 나머지가 있으면 한 페이지를 더 추가
        }
        this.totalPage = totalPageRes;
    }

    public void setStagePage() {
        int stagePageRes = ((page - 1) / countPage) * countPage + 1;
        this.stagePage = stagePageRes;
    }

    public void setEndPage() {
        int endPageRes = stagePage + countPage - 1;
        if (endPageRes > totalPage) {
            endPageRes = totalPage;  // 끝 페이지가 총 페이지 수를 넘지 않도록 처리
        }
        this.endPage = endPageRes;
    }

    public void setPage(int page) {
        if (totalPage < page) {
            this.page = totalPage;  // 페이지가 총 페이지 수보다 크면 마지막 페이지로 설정
        } else {
            this.page = page;
        }
    }
}
