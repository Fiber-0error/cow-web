const map=new WeakMap()

const ob = new ResizeObserver((entries)=>{
  for (let entry of entries) {
    const handler = map.get(entry.target)
    if (handler){
      // console.log(entry.borderBoxSize[0]);
      handler({
        width:entry.borderBoxSize[0].inlineSize,
        height:entry.borderBoxSize[0].blockSize
      })
    }
  }
})

export const vReSize = {
  mounted(el,binging){
    map.set(el,binging.value)
    ob.observe(el)
  },
  unmounted(el){

  }
}
export default vReSize
