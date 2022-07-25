# guestBookDemo

코드로 배우는 스프링부트 웹 프로젝트   
Part2 : 방명록 프로젝트

- 요구사항 분석
    - 유저는 글을 등록할 수 있어야 한다
    - 유저는 작성 된 글을 조회할 수 있어야 한다
    - 유저는 언제든지 작성 된 글을 수정 및 삭제할 수 있어야 한다
    - 유저는 제목, 내용, 작성자, 제목 + 내용, 제목 + 내용 + 작성자 타입으로 검색할 수 있어야 한다.
    - 사용자는 페이지 별로 작성 된 글을 조회할 수 있어야 한다.
- API 규격서

| API 명  | 방식 | 변수 타입 | 리턴 타입  | API 설명 |
| ------------- | --------- | ------------- | ------------- | ------------- | 
| guestBookMain  |  GET  | PageRequestDto  | PageResultDTO<GuestbookDTO, Guestbook>  |메인 화면에 데이터를 뿌려주는 API. |
| register  | POST  | GuestBookDto  | Long  | 글을 등록하는 API | 
| read | GET | Long | GuestBookDto | 글을 읽어오는 API |
| modify | POST | GuestBookDto | void | 작성 된 글을 수정하는 API | 
| remove | POST | Long | void | 작성 도니 글을 삭제하는 API | 

- 구현사항
    - 기본적인 CRUD
    <img width="799" alt="image" src="https://user-images.githubusercontent.com/36991763/180693437-673b7977-3791-42cf-b068-b220d9391736.png">  
    
    <img width="533" alt="image" src="https://user-images.githubusercontent.com/36991763/180693319-bbd24d99-a66d-4eec-be2e-bcca857307eb.png">  
    
    - 검색 기능  
    <img width="647" alt="image" src="https://user-images.githubusercontent.com/36991763/180693456-604aef16-ef21-4738-900d-a08b025754e1.png">

- 기술스택
    - Spring boot
    - MariaDB
    - JPA
    - QueryDSL
    - Thymeleaf
    - Bootstrap

- 개선 해야 할 점  
  - 부트스트랩 템플릿을 더 깔끔한 걸로 교체 할 필요가 있음
      - Vendor 설정이랑 현재 템플릿이랑 약간 안맞음
