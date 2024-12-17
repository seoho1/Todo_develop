##  일정 APP 소개
기존 일정 앱에서 일정이 있었고 작성자와 댓글이 데이터베이스에 추가되므로 서로 연관관계를 가지므로 외래키를 가집니다. 로그인 기능 추가하였으며 필터를 통해 로그인을 하지 않으면 회원가입을 제외한 모든 API가 호출되지 않습니다. 패스워드 암호화 추가하였음.
댓글은 스케쥴과 다대일이며, 스케쥴은 작성자와 다대일, 댓글과 일대다 관계를 가집니다. 모든 스케쥴을 페이지로 10건 단위로 조회가 가능합니다. 작성일과 수정일은  @EnableJpaAuditing 를 통해서 데이터베이스에 등록될 때 자동으로 등록됩니다.



## API 명세서


https://documenter.getpostman.com/view/40157320/2sAYHzHiKC#f0fe03f3-ea5f-4f0f-a95d-943fcd9fa3f3



## ERD

![image](https://github.com/user-attachments/assets/9bf79fb9-2a12-4486-bc09-c42fae56824a)
