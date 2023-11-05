import Player , {Events } from 'xgplayer';
import 'xgplayer/dist/index.min.css';
export const getVideoHeightWidth = async () => {
    const { width, height } = await document.querySelector('.swiper-slide')?.getBoundingClientRect() as DOMRect;
    return { width, height };
}


export const usePlayer = (el, url) => {
  const player = new Player({
    el,
    url,
    width: getVideoHeightWidth().width,
    height: getVideoHeightWidth().height,
    volume: 0.3,
    controls:true,
    loop: true,
  })
  return player;
}


