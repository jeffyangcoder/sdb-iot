<template>
  <a-result title="多余文件,请删除">
    <template #icon>
      <a-icon type="smile" theme="twoTone" />
    </template>
    <template #extra>
      <a-button type="primary" @click="returnIndex">返回首页</a-button>
    </template>
  </a-result>
</template>
