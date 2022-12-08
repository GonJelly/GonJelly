<template>
  <div class="header">
    <img src="@/assets/ssafy_logo.png" alt="" />
    <div><a href="">로그인</a> | <a href="">게시판</a></div>
  </div>
</template>

<script>
export default {
  name: "TheHeader",
};
</script>

<style scope>
img {
  width: 150px;
}

.header {
  padding: 30px;
  text-align: center;
  box-shadow: 0px 1px 10px rgba(159, 157, 157, 0.3);
}

a {
  font-weight: bold;
  color: #2c3e50;
}

a:hover {
  color: #42b983;
}
</style>
