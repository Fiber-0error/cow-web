import Player , {Events } from 'xgplayer';
import 'xgplayer/dist/index.min.css';
export const getVideoHeightWidth = async () => {
    const { width, height } = await document.querySelector('.swiper-slide')?.getBoundingClientRect() as DOMRect;
    return { width, height };
}

// .xgplayer .xgplayer-progress-played
export const baseVideoConfig = {
  volume: 0.3,
  // loop: true, // 无限循环
  // videoInit: true, //视频初始化
  // cssFullscreen: false, // 样式全屏
  // rotateFullscreen: true, // 能否全屏
  // // rotateFullscreen: true,
  // // ignores: ['time', 'definition'],
  // controls: {
  //   autoHide: false
  // }, // 控制面板
  // playbackRate: [0.5, 0.75, 1, 1.5, 2], // 播放速率
}


export const usePlayer = (el, url) => {
  const player = new Player({
    el,
    url,
    width: getVideoHeightWidth().width,
    height: getVideoHeightWidth().height,
    volume: 0.3,
    controls:true,
  })

  // 通过player调用
  player.on(Events.PLAY, () => {

    // 调用视频播放接口
    console.log('video is palyint')
  })

  player.on(Events.PAUSE, (videoPalyer) => {
    // 调用视频上传进度接口
    console.log('video is pause', videoPalyer)
  })

  return player;
}


