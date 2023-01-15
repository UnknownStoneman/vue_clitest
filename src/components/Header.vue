<template>
  <div class="block"><el-avatar :size="60" :src="circleUrl" class="imgSpan"></el-avatar></div>
</template>

<script>
export default {
  data() {
    return {
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      // squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
      sizeList: ["large", "medium", "small"],
    };
  },
};
</script>

<style scoped>
.block {
  width: 80px;
  height: 80px;
  float: right;
  display: flex;
  align-items: center;
  justify-content: center;
}
.imgSpan{
    height: 60px;
    width: 60px;
}
</style>