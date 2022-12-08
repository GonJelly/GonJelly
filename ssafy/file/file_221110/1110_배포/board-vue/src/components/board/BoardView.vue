<template>
  <div class="regist">
    <h1 class="underline">SSAFY 글 상세보기</h1>
    <div class="regist_form">
      <label> 글번호</label>
      <div class="view">{{ article.articleno }}</div>
      <label> 글제목</label>
      <div class="view">{{ article.subject }}</div>
      <label> 작성자</label>
      <div class="view">{{ article.userid }}</div>
      <label> 조회수</label>
      <div class="view">{{ article.hit }}</div>
      <label> 작성시간</label>
      <div class="view">{{ article.regtime }}</div>
      <label> 내용</label>
      <div class="view">{{ article.content }}</div>

      <div style="padding-top: 15px">
        <a href="" class="btn">수정</a>
        <a href="" class="btn">삭제</a>
        <a href="" class="btn">목록</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardView",
  data() {
    return {
      article: Object,
    };
  },
  created() {
    // 비동기
    // TODO : 글번호에 해당하는 글정보 얻기.
    this.article = {
      articleno: 10,
      userid: "안효인",
      subject: "안녕하세요",
      content: "안녕하세요!!!!",
      hit: 10,
      regtime: "2022-11-08 17:03:15",
    };
  },
};
</script>

<style></style>
