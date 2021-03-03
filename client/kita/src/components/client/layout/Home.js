import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { CLIENT_URL, COURSE_DETAIL_URL } from "../../../utility/UrlConstant";
import SingleCourseSection from "../course/SingleCourseSection";
import SingleCourseWithBannerSection from "../course/SingleCourseWithBannerSection";
import "./course_detail.css";

import ReactDOM from "react-dom";
import CategorySection from "../category/CategorySection";
// import { keyframes } from "styled-components";

function Home() {
  const abrreviateStr = (str) => {
    if (str.length > 30) {
      return str.substring(0, 200) + "...";
    }
  };
  const configBannerAnimation = () => {
    const carousel = document.querySelector(".carousel");
    const carousel_copy = carousel.innerHTML;
    const blocks = document.getElementsByClassName("blocks");
    const numberBlocks = (
      (carousel.offsetWidth * carousel.offsetHeight) /
      (50 * 50)
    ).toFixed(0);

    setTimeout(() => {
      for (let i = 0; i < numberBlocks - 1; i++) {
        carousel.innerHTML += "<div class='blocks'></div>";
        const duration = Math.random() * 5;

        if (blocks && blocks[i]) {
          blocks[i].style.animationDuration = 2 + duration + "s";
          blocks[i].style.animationDelay = 1 + duration + "s";
        }
      }
    }, 1000);
    setTimeout(() => {
      carousel.classList.add("active");
      for (let i = 0; i < numberBlocks - 1; i++) {
        const duration = Math.random() * 5;
        if (blocks && blocks[i]) {
          blocks[i].style.animationDuration = 2 + duration + "s";
          blocks[i].style.animationDelay = 2 * duration + "s";
        }
      }
    }, 15000);

    setInterval(() => {
      carousel.innerHTML = carousel_copy;
      carousel.classList.remove("active");

      setTimeout(() => {
        for (let i = 0; i < numberBlocks - 1; i++) {
          carousel.innerHTML += "<div class='blocks'></div>";
          const duration = Math.random() * 5;
          if (blocks && blocks[i]) {
            blocks[i].style.animationDuration = 2 + duration + "s";
            blocks[i].style.animationDelay = 1 + duration + "s";
          }
        }
      }, 0);
      setTimeout(() => {
        carousel.classList.add("active");
        for (let i = 0; i < numberBlocks - 1; i++) {
          const duration = Math.random() * 5;

          if (blocks && blocks[i]) {
            blocks[i].style.animationDuration = 2 + duration + "s";
            blocks[i].style.animationDelay = 2 * duration + "s";
          }
        }
      }, 15000);
    }, 30000);
  };

  useEffect(() => {
    configBannerAnimation();
  }, []);

  return (
    <div className="Home">
      <div className="box"></div>

      {/* BEGIN BANNER */}
      <section className="banner">
        <section className="carousel row">
          {/* {setTimeout(() => {
            clickHdl();
            return; 
          }, 4000)} */}
          {/* <div
            style={{
              visibility: "hidden"
            }}
            className="blocks"
          ></div> */}

          <div className="carousel-caption">
            <div className="container-fluid">
              <h2 className="caption-title">Learn on your schedule</h2>
              <div className="caption-text">
                Study any topic, anytime. Choose from thousands of expert-led
                courses now.
              </div>
              <form className="input-group nav-search caption-search">
                <input
                  type="text"
                  className="form-control"
                  placeholder="What do you want to learn?"
                />
                <div className="input-group-append">
                  <button className="btn bg-white text-danger">
                    <i className="fa fa-search" />
                  </button>
                </div>
              </form>
            </div>
          </div>
        </section>
        <div className="banner-bottom">
          <div className="container-fluid">
            <div className="row">
              <div className="col-md-4 d-flex align-items-center justify-content-center">
                <span>
                  <i className="fa fa-cubes" />
                </span>
                <div className="banner-bottom-text">
                  <b>100,000 online courses</b>
                  <div className="sub-title">
                    Explore a variety of fresh topics
                  </div>
                </div>
              </div>
              <div className="col-md-4 d-flex align-items-center justify-content-center">
                <span>
                  <i className="fa fa-joomla" />
                </span>
                <div className="banner-bottom-text">
                  <b>Expert instruction</b>
                  <div className="sub-title">
                    Find the right instructor for you
                  </div>
                </div>
              </div>
              <div className="col-md-4 d-flex align-items-center justify-content-center">
                <span>
                  <i className="fa fa-gg" />
                </span>
                <div className="banner-bottom-text">
                  <b>Lifetime access</b>
                  <div className="sub-title">Learn on your schedule</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* END BANNER */}
      {/* BENGIN SELLER */}
      <SingleCourseWithBannerSection
        title={"Best Seller"}
        url={"http://localhost:8080/api/course/best-seller"}
      />
      {/* END SELLER */}
      {/* BEGIN POPULAR */}

      <SingleCourseSection
        title={"Top Discount"}
        url={"http://localhost:8080/api/course/top-discount"}
      />
      <CategorySection url={"http://localhost:8080/api/category/top"} />
      <div>
        <SingleCourseSection
          title={"Popular Courses"}
          url={"http://localhost:8080/api/course/popular"}
        />
        <SingleCourseSection
          title={"All Courses"}
          url={"http://localhost:8080/api/course"}
          endPage={true}
        />
      </div>
      <div className="container-fluid">
        <div className="row"></div>
      </div>
    </div>
  );
}
export default Home;
