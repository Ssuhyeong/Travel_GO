# TRAVEL GO ! 
# 1.개요
<img src="https://github.com/Ssuhyeong/trip_project/assets/107913312/ebb5093d-b4fd-4298-a18a-507ec70df8af" width = "300" height = "300">   


> 진행기간 : 2023.05.02 ~ 2023.05.25
> 
> 서비스명 : Travel Go !
> 
> 한줄 소개 : 여행일정을 계획하는것에 어려움이 있는 사람들을 위한 여행 경로 추천 사이트
> 
# 2. 팀원 소개
|손수형|최경인|
|---|---|
|img|<img src="https://github.com/Ssuhyeong/trip_project/assets/107913312/705e990f-56c5-4442-8305-be57ea4e4efb" width = "100" height = "100">|
|[@Ssuhyeong](https://github.com/Ssuhyeong)|[@Fizioo0102](https://github.com/Fizioo0102)|
|Front-end|Back-end|

# 3. 기술 스택

### Communication
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=white">
  
### Development
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

### Tool
  <img src="https://img.shields.io/badge/visualstudiocode-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white"> <img src="https://img.shields.io/badge/intellijidea-7952B3?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white"> <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white">

# 4. 화면 구성
### 메인 페이지
![image](https://github.com/Ssuhyeong/trip_project/assets/107913312/ab7851b2-ded8-45e3-adfe-690c61d0602e)    
![image](https://github.com/Ssuhyeong/trip_project/assets/107913312/3dbb3cf8-0fd5-439b-973d-1f5a09546782)    
![image](https://github.com/Ssuhyeong/trip_project/assets/107913312/735b3512-ec62-4301-9da4-b59f2bfb7e57)

### 마이페이지
![image](https://github.com/Fizioo0102/trip_project/assets/107913312/1a882ff2-a2bc-4753-8583-2cd10fd0c0d4)    

#### 핫플레이스
![image](https://github.com/Fizioo0102/trip_project/assets/107913312/8c2684f4-e18b-4c84-8bb2-0de39fb36e8f)

- 지역 별,카테고리 별 관광지를 좋아요가 많은 순서에 따라 추천 해 주고 있습니다.

#### 상세 정보 페이지
![image](https://github.com/Fizioo0102/trip_project/assets/107913312/97427912-9756-4ac1-8906-bba93fdee2ce)
![image](https://github.com/Fizioo0102/trip_project/assets/107913312/bc959a0f-3eaf-4fc7-bb9f-1b085e8ff89a)
![image](https://github.com/Fizioo0102/trip_project/assets/107913312/45539413-5a7b-49c0-86b1-c3cee7282790)

- Pagination을 적용하여 한 페이지에 15개의 관광지가 표시 됩니다.
- 관광지 한개 당 평균별점,리뷰어수,좋아요 수 가 표시됩니다.
- 화면 상단에서 날씨 API를 활용하여 지역 별 날씨를 확인 할 수 있습니다.
- 추천 과 경로 버튼을 통하여 정렬된 결과를 출력 할 수 있습니다.
- 검색창을 통하여 원하는 키워드의 관광지를 검색 할 수 있습니다.
- 원하는 관광지의 상세페이지에 들어가게 되면 상세정보와 함께 리뷰 작성, 좋아요 등록/취소, 카카오톡을 활용한 링크 공유의 기능을 이용 할 수 있습니다.

#### 여행 경로 페이지
![image](https://github.com/Fizioo0102/trip_project/assets/107913312/616724bb-f960-4bee-b70f-26d3fa88d42e)
![image](https://github.com/Fizioo0102/trip_project/assets/107913312/9fccdc8a-ff65-4f73-8cd1-a63d582c9d9a)

- 사용자가 좋아요 한 여행지 리스트를 기반으로 Drag & Drop 을 이용하여 여행 일정을 수정 변경 할 수 있습니다.
- 일정이 FIX 된 후에는 하버사인 공식을 사용하여 구해진 거리가 여행 일정과 함께 사용자에게 타임라인으로 보여지게 됩니다.
- 또한 FIX된 일정은 마이페이지 에서 조회가 가능합니다.

# 5. DB
![ERD최종](https://github.com/Fizioo0102/trip_project/assets/107913312/05b7765e-5326-4fd9-bf14-d5016e795737)

# 6. 아키텍처
### 디렉토리 구조

```
─src          (BACK-END)
│      └─main
│          ├─generated
│          │  └─com
│          │      └─ssafy
│          │          └─trip
│          │              └─Entity
│          │                      QAttraction.java
│          │                      QBoard.java
│          │                      QFaq.java
│          │                      QHeart.java
│          │                      QMember.java
│          │                      QOpenBoard.java
│          │                      QPhoto.java
│          │                      QReview.java
│          │                      QSchedule.java
│          │                      QTravelRoutes.java
│          │
│          ├─java
│          │  └─com
│          │      └─ssafy
│          │          └─trip
│          │              │  TripApplication.java
│          │              │
│          │              ├─aop
│          │              │      LoggingAspect.java
│          │              │
│          │              ├─configuration
│          │              │      CustomWebMvcConfigurer.java
│          │              │      QuerydslConfig.java
│          │              │      WebSecurityConfig.java
│          │              │
│          │              ├─controller
│          │              │      AttractionController.java
│          │              │      BoardController.java
│          │              │      FaqController.java
│          │              │      HeartController.java
│          │              │      OpenBoardController.java
│          │              │      ProfileController.java
│          │              │      ReviewController.java
│          │              │      ScheduleController.java
│          │              │      TravelRoutesController.java
│          │              │      UserController.java
│          │              │
│          │              ├─dto
│          │              │  │  UserSignDto.java
│          │              │  │
│          │              │  ├─request
│          │              │  │      BoardRequestDto.java
│          │              │  │      HeartRequestDto.java
│          │              │  │      MemberRequestDto.java
│          │              │  │      OpenBoardRequestDto.java
│          │              │  │      PhotoRequestDto.java
│          │              │  │      ReviewRequestDto.java
│          │              │  │      ScheduleRequestDto.java
│          │              │  │      TravelRoutesRequestDto.java
│          │              │  │
│          │              │  └─response
│          │              │          BoardResponseDto.java
│          │              │          OpenBoardResponseDto.java
│          │              │          ReviewResponseDto.java
│          │              │
│          │              ├─Entity
│          │              │      Attraction.java
│          │              │      Board.java
│          │              │      Faq.java
│          │              │      Heart.java
│          │              │      Member.java
│          │              │      OpenBoard.java
│          │              │      Photo.java
│          │              │      Review.java
│          │              │      Role.java
│          │              │      Schedule.java
│          │              │      TravelRoutes.java
│          │              │
│          │              ├─handler
│          │              │      FileHandler.java
│          │              │      LoginFailHandler.java
│          │              │      LogInSuccessHandler.java
│          │              │
│          │              ├─interceptor
│          │              │      ConfirmInterceptor.java
│          │              │
│          │              ├─jwt
│          │              │      CustomJsonUsernamePasswordAuthenticationFilter.java
│          │              │      JwtAuthenticationProcessingFilter.java
│          │              │      JwtKey.java
│          │              │      MemberController.java
│          │              │      SecurityConfig.java
│          │              │
│          │              ├─repository
│          │              │  │  MemberRepository.java
│          │              │  │  PhotoRepository.java
│          │              │  │  UserRepository.java
│          │              │  │
│          │              │  ├─attracion
│          │              │  │      AttractionRepository.java
│          │              │  │      AttractionRepositoryCustom.java
│          │              │  │      AttractionRepositoryImpl.java
│          │              │  │
│          │              │  ├─board
│          │              │  │      BoardRepository.java
│          │              │  │      BoardRepositoryCustom.java
│          │              │  │      BoardRepositoryImpl.java
│          │              │  │      FaqRepository.java
│          │              │  │      OpenBoardRepository.java
│          │              │  │      OpenBoardRepositoryCustom.java
│          │              │  │      OpenBoardRepositoryImpl.java
│          │              │  │      ReviewRepository.java
│          │              │  │
│          │              │  ├─heart
│          │              │  │      HeartRepository.java
│          │              │  │      HeartRepositoryCustom.java
│          │              │  │      HeartRepositoryImpl.java
│          │              │  │
│          │              │  └─Schedule
│          │              │          ScheduleRepository.java
│          │              │          ScheduleRepositoryCustom.java
│          │              │          ScheduleRepositoryImpl.java
│          │              │          TravelRoutesRepository.java
│          │              │          TravelRoutesRepositoryCustom.java
│          │              │          TravelRoutesRepositoryImpl.java
│          │              │
│          │              └─service
│          │                  │  AttractionService.java
│          │                  │  HeartService.java
│          │                  │  JwtService.java
│          │                  │  LoginService.java
│          │                  │  MemberService.java
│          │                  │  ReviewService.java
│          │                  │  ScheduleService.java
│          │                  │  TravelRoutesService.java
│          │                  │
│          │                  └─board
│          │                          BoardService.java
│          │                          FaqService.java
│          │                          OpenBoardService.java
│          │
│          └─resources
│              │  application.yml
│              │
│              └─static
│                  └─img
│                      └─photo
│                              default.jpg
```






