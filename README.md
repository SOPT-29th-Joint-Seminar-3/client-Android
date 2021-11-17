# client-Android

**참고**

[git 사용법](https://github.com/TeamMascota/Mascota-Android/wiki/1.-Git-%EC%82%AC%EC%9A%A9%EB%B2%95)

[Android Coding Convention](https://github.com/TeamMascota/Mascota-Android/wiki/2.-Android-Coding-Convention)

# [Git]
### 1.Git Flow
1. Issue를 생성한다.
2. feature Branch를 생성한다.
3. Add - Commit - Push - Pull Request 의 과정을 거친다.
4. Pull Request가 작성되면 작성자 이외의 다른 팀원이 Code Review를 한다.
5. Code Review가 완료되면 Pull Request 작성자가 develop Branch로 merge 한다.
6. merge된 작업이 있을 경우, 다른 브랜치에서 작업을 진행 중이던 개발자는 본인의 브랜치로 merge된 작업을 Pull 받아온다.
7. 종료된 Issue와 Pull Request의 Label과 Project를 관리한다.

### 2.Branch Naming Rule
Branch를 생성하기 전 Issue를 먼저 작성한다. Issue 작성 후 생성되는 번호와 Issue의 간략한 설명 등을 조합하여 Branch의 이름을 결정한다. <Prefix>/<Issue_Number>-<Description> 의 양식을 따른다.

#### 2.1 Prefix
`main` : 개발이 완료된 산출물이 저장될 공간
  
`dev` : feature 브랜치에서 구현된 기능들이 merge될 브랜치
  
`feature` : 기능을 개발하는 브랜치, 이슈별/작업별로 브랜치를 생성하여 기능을 개발한다

  
###  3. Commit
  
  [ < PREFIX > ] < Issue_Number > - < Description > 의 양식을 준수한다.

`FIX` : 버그, 오류 해결 [FIX] #10 - callback error
  
`ADD` : Feat 이외의 부수적인 코드 추가, 라이브러리 추가, 새로운 View나 Activity 생성 [ADD] #11 - LoginActivity [ADD] #12 - CircleImageView Library
  
`FEAT` : 새로운 기능 구현 [FEAT] #11 - google login
  
`DEL` : 쓸모없는 코드 삭제 [DEL] #12 - unnecessary import package
  
`DOCS` : README나 WIKI 등의 문서 개정 [DOCS] update readme
  
`REFACTOR` : 내부 로직은 변경 하지 않고 기존의 코드를 개선하는 리팩토링 시 [REFACTOR] #15 - MVP architecture to MVVM
  
`CHORE` : 그 이외의 잡일/ 버전 코드 수정, 패키지 구조 변경, 파일 이동, 가독성이나 변수명, reformat 등 [CHORE] #20 - delete unnecessary import package [CHORE] #21 - reformat MainActivity
  
`MOD` : xml 파일만 수정한 경우 [MOD] #30 - use constraintlayout in activity_main.xml
  
`TEST` : 테스트 코드 추가
  
###   4. Pull Request
  develop, main으로 merge할 때에는 Pull Request와 다른 팀원의 Code Review가 필요하다.
  
#### 4.1 Code Review
Merge를 위해 Pull Request에 대한 팀원들의 Code Review를 수행한다. Code Review는 변경 사항에 대해 궁금한 점, 코드 가독성(변수명, 함수명 등)에 대한 조언 등을 작성한다. Code Review는 존댓말로 하며, 일방적인 시비나 비난은 금지한다. Pull Request 작성자는 Code Review에 대해 성실히 답변한다.


#   [Android Coding Convention]
  
### 1. Resource Naming Convention
### 1.1 ID
   `Snake Case` 규칙을 따른다
```
@+id/tv_login
@+id/et_password
@+id/btn_login
```
### 1.2 Layout
  `<WHAT>_<WHERE>` 규칙을 따른다
  ```
SignInActivity.kt -> activity_sign_in.xml
SignUpFragment.kt -> fragment_sign_up.xml
CustomCalendarView.kt -> view_custom_calendar.xml
item_user.xml
```
### 1.3 Drawable
  `<WHAT_DESCRIPTION>`
  ```
ic_error.xml
img_default_user.xml
bg_main.xml
rectangle_yellow_radius_20.xml
```
### 1.4 Color
  `Camel Case` 규칙을 따르되 디자인 에셋 네이밍을 기본으로 한다
  ```
<color name="black">#FF000000</color>
<color name="white">#FFFFFFFF</color>
<color name="cornflowerBlue">#6195ED</color>
```
### 1.5 String
  `<WHERE/WHAT>_<DESCRIPTION>`
  ```
  <!--Main Menu-->
<string name="menu_daily">하루의 기록</string>
<string name="menu_remind">평가 및 회고</string>
<string name="menu_my">My</string>

<!--Toolbar Title-->
<string name="title_search">검색</string>
<string name="title_settings">환경설정</string>

<!--Dialog Message-->
<string name="msg_login">로그인하시겠습니까?</string>
<string name="msg_login_failed">로그인에 실패했습니다.</string>
<string name="msg_password_error">비밀번호가 올바르지 않습니다.</string>
  ```
 ### 1.6 Style
  `<WHAT><Description>Style`
  ```
  <style name="LoginEditTextStyle"/>
<style name="MainDialogStyle"/>
  ```
  
  ### 2.  Kotlin Naming Convention
  ### 2.1 Class
  `UpperCamelCase`
  ```
MainActivity
UserViewModel
WriteFragment
```
  ### 2.2 Method
  `lowerCamelCase`
  "동사"로 시작하는 "동사구" 형태를 사용하되, 동사 원형만을 사용한다.
자주 사용하는 동사는 용법에 맞게 사용한다.
 #### Word	Description
`show`	Invisible한 것을 Visible하게 바꾸는 동작
`check`	어떤 것을 확인한 후 boolean 또는 값으로 반환하는 동작
`is`	어떤 것인지 확인한 후 boolean으로 반환하는 동작
`has`	어떤것을가지고 있는 확인 후 boolean으로 반환하는 동작

### 2.3 Variable
  `lowerCamelCase`
  ```
  isEnd
viewPagerAdapter
  ```
