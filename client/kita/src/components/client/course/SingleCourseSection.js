import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import CalculatePromotionPriceCourse from "../../../utility/CalculatePromotionPriceCourse";
import { CLIENT_URL, COURSE_DETAIL_URL } from "../../../utility/UrlConstant";
import SkeletonLoader from "../../fragment/SkeletonLoader";

function SingleCourseSection(props) {
  const { icon, url, title, endPage, data } = props;
  const [loading, setLoading] = useState(true);
  let [courses, setCourses] = useState([]);
  if (data) {
    courses = data;
  }
  let memoCourses = [];
  const handleSroll = async () => {
    const shouldLoadMore =
      window.innerHeight + window.scrollY >= document.body.offsetHeight;

    if (shouldLoadMore) {
      window.removeEventListener("scroll", handleSroll);
      setLoading(true);
      fetchData();
      // fetchData().then(async () => {
      //   // setLoading(false);

      // });
    }
  };
  const addScrollEvent = () => {
    setTimeout(() => {
      window.addEventListener("scroll", handleSroll);
    }, 200);
  };
  useEffect(() => {
    if (!data) {
      fetchData();

      return;
    }
    setLoading(false);
  }, []);
  const fetchData = async () => {
    if (endPage) {
      const newCoursse = await fetchCourses();
      if (newCoursse) {
        memoCourses = memoCourses.concat(newCoursse);
        setCourses(memoCourses);
        // window.scrollTo(0, 0);
        endPage && addScrollEvent();
        // setTimeout(() => {
        // }, 0);
        return;
      }
      setLoading(false);
      return;
    }
    setCourses(await fetchCourses());
  };
  const fetchCourses = async () => {
    const pageNumber = memoCourses == [] ? 0 : memoCourses.length / 6;
    const page = "?page=" + pageNumber;
    const data = await axios({
      method: "GET",
      url: url + page,
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
    return data;
  };
  return (
    <div className="SingleCourseSection">
      <section className="feedback">
        <div className="container-fluid">
          <h3 className="title mt-3">
            <i className={icon}></i>
            {title}
          </h3>

          <div className="row">
            {courses &&
              courses.map((course) => (
                <div key={course.id} className="col-md-2">
                  <div className="course">
                    <img src={course.image} />{" "}
                    <h6 className="course-title">{course.title}</h6>
                    <p class="font-italic text-center">
                      <i className="fa fa-user"></i>
                      {" " + course.students + " students"}
                    </p>
                    <small className="course-content">
                      {course.description}
                    </small>
                    {course.price != 0 && (
                      <div className="seller-label">
                        {course.discount + "%"}
                      </div>
                    )}
                    <div className="course-price">
                      {course.price == 0 ? (
                        <>
                          <span>Free</span>
                        </>
                      ) : (
                        <>
                          <span>{"$" + course.price}</span>
                          <small class="text-right ml-2">
                            {"$" +
                              CalculatePromotionPriceCourse(
                                course.price,
                                course.discount
                              )}
                          </small>
                        </>
                      )}
                    </div>
                    <div className="course-overlay">
                      <a>
                        <h6 className="course-title">{course.title}</h6>
                        <div className="course-author">
                          <b>{course.author}</b>
                          <span className="mx-1"> | </span>
                          <b>{course.category.title}</b>
                        </div>
                        <div className="course-info">
                          <span>
                            <i className="fa fa-play-circle" />
                            {" " + course.lecturerCount + " "}
                            lectures
                          </span>
                          <span className="mx-1"> | </span>
                          <span>
                            <i className="fa fa-clock-o" />
                            {" " + course.lengthVideos + " "}
                            hours
                          </span>
                        </div>
                        {/* <span className="">
                            <i className="fa fa-clock-o" />
                            {" " + course.students + " "}
                            hours
                          </span> */}
                        <small className="course-content">
                          {course.description}
                        </small>
                      </a>
                      <Link
                        to={CLIENT_URL + COURSE_DETAIL_URL + "/" + course.id}
                        className="btn btn-sm btn-danger text-white w-100"
                      >
                        Enroll now
                      </Link>
                    </div>
                  </div>
                </div>
              ))}
          </div>
          {loading && <SkeletonLoader width={window.innerWidth} />}
        </div>
      </section>
    </div>
  );
}
export default SingleCourseSection;
