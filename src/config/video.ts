
export const getVideoHeightWidth = () => {
  const { width, height } = document.querySelector('.swiper-slide')?.getBoundingClientRect();
  return { width, height };
}

export const baseVideoConfig = {
  autoplay: true,
  loop: true,
  // fitVideoSize: 'width',
  videoInit: true,
  // fluid: true,
  cssFullscreen: false, // 样式全屏
  rotateFullscreen: true, // 能否全屏
  // rotateFullscreen: true,
  // ignores: ['time', 'definition'],
  // width: getVideoHeightWidth().width - 10,
  // height: getVideoHeightWidth().height - 10,
  // width: getVideoHeightWidth() | 0,
  controls: true, // 控制面板
  playbackRate: [0.5, 0.75, 1, 1.5, 2],
}



