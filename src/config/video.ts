import Player , {Events } from 'xgplayer';
import 'xgplayer/dist/index.min.css';
export const getVideoHeightWidth = async () => {
    const { width, height } = await document.querySelector('.swiper-slide')?.getBoundingClientRect() as DOMRect;
    return { width, height };
}


export const usePlayer = (el?: any, url?: any, width?:any, height?: any, controls = true) => {
  const player = new Player({
    el,
    url,
    width: width ? width : getVideoHeightWidth().width,
    height: height ? height : getVideoHeightWidth().height,
    volume: 0.3,
    controls,
    loop: true,
  })
  return player;
}

