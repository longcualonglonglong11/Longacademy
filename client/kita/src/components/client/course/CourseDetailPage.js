import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import ScriptTag from "react-script-tag/lib/ScriptTag";
import "../../../assets/client/css/course.css";
import CalculatePromotionPriceCourse from "../../../utility/CalculatePromotionPriceCourse";
import { CLIENT_URL, HOMEPAGE_URL } from "../../../utility/UrlConstant";
import SkeletonLoader from "../../fragment/SkeletonLoader";
import Plyr from "plyr-react";
import "plyr-react/dist/plyr.css";
import { useSelector } from "react-redux";

function CourseDetailPage({ match }) {
  const user = useSelector((state) => state.user);

  const { courseId } = match.params;
  const [loading, setLoading] = useState(true);
  const [course, setCourse] = useState([]);
  const isBuy = user.data.buyedCourse.find((buyedCourse) => {
    return buyedCourse.id === course.id;
  });
  const [expandVideo, setExpandVideo] = useState(true);
  useEffect(() => {
    setCourse(fetchCourseDetail());
  }, []);
  const fetchCourseDetail = async () => {
    const url = "http://localhost:8080/api/course";
    const data = await axios({
      method: "GET",
      url: `${url}/${courseId}`,
      headers: {
        Public: "public",
      },
    })
      .then((response) => {
        setLoading(false);
        return response.data;
      })
      .catch((err) => {
        console.log(err);
      });
    if (data) {
      data.targetDtos = await data.targetDtos.reduce(
        (targets, target, index) => {
          if (index % 2 == 0) {
            targets.targetLeftSide.push(target);
            return targets;
          }
          targets.targetRightSide.push(target);
          return targets;
        },
        { targetLeftSide: [], targetRightSide: [] }
      );
    }

    // console.log(data);
    setCourse(data);
  };
  const toggleVideos = (e) => {
    e.stopPropagation();
    const flexOperator = document.querySelector(".flex-operator");
    document.querySelector("#list-content").classList.toggle("show");
    const minusIcon = "fa-minus";
    const plusIcon = "fa-plus";
    if (flexOperator.classList.contains(minusIcon)) {
      flexOperator.classList.remove(minusIcon);
      flexOperator.classList.add(plusIcon);
    } else {
      flexOperator.classList.remove(plusIcon);
      flexOperator.classList.add(minusIcon);
    }
  };
  const CourseDetail = ({ course }) => (
    <div>
      <nav aria-label="breadcrumb">
        <div className="container-fluid">
          <ol className="breadcrumb bg-white mb-0 py-2">
            <li className="breadcrumb-item">
              <Link to={CLIENT_URL + HOMEPAGE_URL}>
                <i className="fa fa-home mr-1" /> Home
              </Link>
            </li>
            <li className="breadcrumb-item">
              {course && course.categoryDto && (
                <Link to={CLIENT_URL + "/category/" + course.categoryDto.id}>
                  <i
                    class={course && course.category && course.category.icon}
                  ></i>{" "}
                  {course && course.category && course.category.title}
                </Link>
              )}
            </li>

            <li className="breadcrumb-item active" aria-current="page">
              {course && course.title}
            </li>
          </ol>
        </div>
      </nav>
      <section className="course-banner">
        <div className="container">
          <div className="banner-content">
            <h1>{course.title}</h1>
            <h5>{course.description}</h5>
            <h6 className="mt-3">
              <span>
                <i className="fa fa-user m-1" /> Created by{" "}
              </span>
              <a>{course.author}</a>
              <span class="ml-3">
                <i className="fa fa-calendar-check-o mr-1" />{" "}
                {String(course.lastUpdate).substring(0, 10)}
              </span>
            </h6>
            <h6 className="mt-3">
              <span>
                <i className="fa fa-play-circle mr-1" /> {course.lecturerCount}{" "}
                lectures
              </span>
              <span className="mx-1"> | </span>
              <span>
                {" "}
                <i className="fa fa-clock-o mr-1" /> {course.lengthVideos} hours
              </span>
              <span className="ml-2">
                with <b className="mx-1">{course.students}</b> students enrolled
              </span>
            </h6>
          </div>
        </div>
      </section>
      <section className="course-content">
        <div className="container">
          <div className="row">
            <div className="col-md-8">
              {/* BEGIN DESC */}
              <div className="course-desc">
                <h4 className="course-desc-title">Target Of This Course</h4>
                <div className="row">
                  <div className="col-md-6">
                    <ul className="course-desc-items">
                      {course &&
                        course.targetDtos &&
                        course.targetDtos.targetLeftSide &&
                        course.targetDtos.targetLeftSide.map((target) => (
                          <li>
                            <i className="fa fa-check " />
                            <span> {target.title}</span>
                          </li>
                        ))}
                    </ul>
                  </div>
                  <div className="col-md-6">
                    <ul className="course-desc-items">
                      {course &&
                        course.targetDtos &&
                        course.targetDtos.targetRightSide &&
                        course.targetDtos.targetRightSide.map((target) => (
                          <li>
                            <i className="fa fa-check " />
                            <span> {target.title}</span>
                          </li>
                        ))}
                    </ul>
                  </div>
                </div>
              </div>
              {/* END DESC */}
              {/* BEGIN LIST */}
              <div className="course-list">
                <div className="list-header d-flex align-items-center justify-content-between">
                  <h4 className="font-weight-bold">Course content</h4>
                  <div>
                    <span className="mr-3">
                      {course.lecturerCount} lectures
                    </span>
                  </div>
                </div>
                <div className="mt-2">
                  <a
                    onClick={(e) => toggleVideos(e)}
                    className="list-content-first"
                    data-toggle="collapse"
                  >
                    <span>
                      <i className="fa fa-minus mr-1 flex-operator" /> Course
                      list
                    </span>
                    <span>{`${(course.lengthVideos / 60).toFixed(0)}:${
                      course.lengthVideos % 60
                    }`}</span>
                  </a>

                  <ul
                    // onClick={(e) => toggleVideos(e)}
                    id="list-content"
                    className="collapse show"
                  >
                    {course &&
                      course.videoDtos &&
                      course.videoDtos.map((video) => (
                        <li className="stop-point">
                          <a
                            onClick={(e) => {
                              let plyrItem = e.target;

                              while (
                                !plyrItem.classList.contains("stop-point")
                              ) {
                                plyrItem = plyrItem.parentElement;
                              }
                              if (!plyrItem.classList.contains("active")) {
                                plyrItem.classList.add("active");

                                const titles = plyrItem.querySelectorAll(
                                  "a span"
                                );
                                titles[0].querySelector(".fa").style.color =
                                  "#00b3ff";
                                titles[1].style.color = "#00b3ff";

                                plyrItem = plyrItem.querySelector(
                                  ".video-container"
                                );
                                plyrItem.style = {
                                  width: "100%",
                                  height: "100%",
                                };
                                console.log(plyrItem);
                              } else {
                                plyrItem.classList.remove("active");

                                const titles = plyrItem.querySelectorAll(
                                  "a span"
                                );
                                titles[0].querySelector(".fa").style.color =
                                  "#a1a7b3";
                                titles[1].style.color = "#a1a7b3";

                                plyrItem = plyrItem.querySelector(
                                  ".video-container"
                                );
                                plyrItem.style.width = 0;
                                plyrItem.style.height = 0;
                              }
                            }}
                          >
                            <span>
                              {" "}
                              <i className="fa fa-play-circle mr-1">
                                {" "}
                                {video.title}
                              </i>
                            </span>
                            <span>
                              {" "}
                              {`${(video.length / 60).toFixed(0)}:${
                                video.length % 60
                              }`}
                            </span>
                          </a>
                          <div
                            class="video-container"
                            style={{
                              width: 0,
                              height: 0,
                            }}
                          >
                            {isBuy && (
                              <Plyr
                                source={{
                                  type: "video",
                                  sources: [
                                    {
                                      src: video.url,
                                      provider: "youtube",
                                    },
                                  ],
                                }}
                              />
                            )}
                          </div>
                        </li>
                      ))}
                  </ul>
                </div>
              </div>
              {/* END LIST */}
              {/* BEGIN INFO */}
              <div className="course-info mt-4">
                <h4 className="font-weight-bold">Description</h4>
                <div className>
                  {/* <p className="font-weight-bold">{course.description}</p> */}
                  <p className="font-italic">{course.content}</p>
                </div>
              </div>
              {/* END INFO */}
            </div>
            <div className="col-md-4">
              <div className="course-buy">
                {course.price == 0 ? (
                  <h2 className="font-weight-bolder">Free</h2>
                ) : (
                  <>
                    <h2 className="mb-4 font-weight-bold">
                      $
                      {CalculatePromotionPriceCourse(
                        course.price,
                        course.discount
                      )}
                    </h2>{" "}
                    <p>
                      <del>
                        ${course && course.price && course.price.toFixed(2)}
                      </del>
                    </p>
                  </>
                )}
                {console.log(user)}
                {user && user.data && !isBuy ? (
                  user &&
                  user.data &&
                  user.data.courseInCart.find((courseIncart) => {
                    return courseIncart.id === course.id;
                  }) ? (
                    <>
                      <button className="btn btn-danger w-100">
                        Remove from cart
                      </button>
                      <button className="btn btn-outline-secondary w-100">
                        Buy now
                      </button>
                    </>
                  ) : (
                    <>
                      <button className="btn btn-danger w-100">
                        Add to cart
                      </button>
                      <button className="btn btn-outline-secondary w-100">
                        Buy now
                      </button>
                    </>
                  )
                ) : (
                  // <button className="btn btn-outline-secondary w-100">
                  //   Buy nuu
                  // </button>
                  <></>
                )}
                <br />

                <div className="course-buy-info mt-2">
                  <span>This course includes</span>
                  <small>
                    <i className="fa fa-play-circle-o" /> 24 hours on-demand
                    video
                  </small>
                  <small>
                    <i className="fa fa-file-o" /> 19 articles
                  </small>
                  <small>
                    <i className="fa fa-code" /> 19 coding exercises
                  </small>
                  <small>
                    <i className="fa fa-empire" /> Full lifetime access
                  </small>
                  <small>
                    <i className="fa fa-tablet" /> Access on mobile and TV
                  </small>
                  <small>
                    <i className="fa fa-recycle" /> Certificate of Completion
                  </small>
                </div>
                <a className="course-buy-share border-top" href="#">
                  <i className="fa fa-share" /> Share
                </a>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
  return (
    <div classname="CourseDetailPage">
      <div class="ml-4 mt-2">
        {loading && <SkeletonLoader width={window.innerWidth} num={50} />}
      </div>
      {course && <CourseDetail course={course} />}
    </div>
  );
}

export default CourseDetailPage;
