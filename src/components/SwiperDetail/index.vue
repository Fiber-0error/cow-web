<template>
  <div :style="{ width: width + '!important' }" class="thumb-example">
    <div style="height: 700px;">
      <template v-if="getImageList">
        <!-- swiper1 -->
        <div id="swiperTop" class="swiper">
          <div class="swiper-wrapper">
            <div
              v-for="(item, index) in swiperImageList"
              :key="index"
              class="swiper-slide"
            >
              <div style="width: 550px;height: 550px;">
                <el-image
                  ref="detailImage"
                  :src="item"
                  class="filters-img"
                  fit="contain"
                >
                  <div
                    slot="error"
                    style="display: flex;justify-content: center;align-items: center;height: 100%"
                  >
                    <img
                      slot="error"
                      :src="getImage('')"
                      alt="error"
                      class="filters-img"
                      style="width: 160px;height: 160px"
                    />
                  </div>
                </el-image>
              </div>
            </div>
          </div>
        </div>
        <!--            swiper2 Thumbs-->
        <div id="swiperThumbs" class="swiper" style="margin-top: 16px">
          <div class="swiper-wrapper">
            <div
              v-for="(item, index) in swiperImageList"
              :key="index"
              class="swiper-slide swiperThumbs"
            >
              <el-image
                ref="detail-image"
                :src="item"
                class="filters-img"
                fit="contain"
              >
                <img
                  slot="error"
                  :src="getImage('')"
                  alt=""
                  class="filters-img"
                />
              </el-image>
            </div>
          </div>
        </div>
        <div
          style=" line-height: 48px;display: flex;justify-content: space-between"
        >
          <div style="font-size: 16px;font-weight: 400;color: black">
            {{ activeIndex }} of {{ swiperImageList.length }}
          </div>
          <div style="display: flex">
            <div class="swiper-button-prev">
              <i class="el-icon-arrow-left"></i>
            </div>
            <div class="swiper-button-next">
              <i class="el-icon-arrow-right"></i>
            </div>
          </div>
        </div>
      </template>
    </div>
  </div>
</template>

<script>
// import Swiper from "swiper";
// // import styles bundle
// import "swiper/swiper-bundle.min.css";
import Swiper from "swiper/swiper-bundle";
import "swiper/swiper.css";
import { commonUtils } from "@/utils/common";

export default {
  components: {},
  props: {
    imageList: {
      type: Array,
      default: () => {
        return [];
      }
    },
    width: {
      type: String,
      default: "552px"
    },
    height: {
      type: String,
      default: "552px"
    }
  },
  data () {
    return {
      swiperImageList: [],
      srcList: [],
      activeIndex: 0,
      getImageList: false
    };
  },
  watch: {
    imageList (val) {
      this.swiperImageList = [];
      const length = (val && val.length) || 0;
      if (length) {
        this.swiperImageList = val;
      } else {
        this.swiperImageList.push("");
      }
      this.getImageList = true;
    },
    swiperImageList: function() {
      /*DOM还没更新*/
      this.$nextTick(function() {
        this.renderSwiper();
        /*DOM更新了*/
        /*现在数据已经渲染完毕*/
      });
    }
  },
  mounted () {
    this.getImageList = true;
  },
  methods: {
    getImage (imageUri) {
      return commonUtils.getImageUrl(imageUri);
    },
    renderSwiper () {
      new Swiper("#swiperTop", {
        spaceBetween: 10,
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev"
        },
        thumbs: {
          swiper: new Swiper("#swiperThumbs", {
            slidesPerView: 5,
            spaceBetween: 38
          }),
          autoScrollOffset: 0.5
        },
        on: {
          slideChange: swiper => {
            this.activeIndex = swiper.activeIndex;
          }
        }
      });
      return true;
    }
  }
};
</script>

<style lang="scss" scoped>
.thumb-example {
  width: 400px;
}

.swiper {
  .swiper-slide {
    background-size: cover;
    background-position: center;
    text-align: center;
  }

  .swiperThumbs {
    box-sizing: border-box;
    border: 1px solid gray;
    opacity: 0.4;
    width: 80px;
    height: 80px;
  }

  .swiper-slide-thumb-active {
    opacity: 1;
    border: 2.5px solid #007bc0;
  }
}

.swiper-button-next,
.swiper-button-prev {
  font-size: 24px;
  //background-color: #0e78c5;
  display: inline-block;
  cursor: pointer;
  width: 48px;
  text-align: center;
}

.swiper-button-disabled {
  opacity: 0.2;
}

.filters-img {
  height: 100%;
}
</style>
