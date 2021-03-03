import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import CalculatePromotionPriceCourse from "../../../utility/CalculatePromotionPriceCourse";
import { CLIENT_URL, COURSE_DETAIL_URL } from "../../../utility/UrlConstant";
import SkeletonLoader from "../../fragment/SkeletonLoader";

function SingleCourseWithBannerSection(props) {
  const { url, title } = props;
  const [loading, setLoading] = useState(true);
  const [courses, setCourses] = useState([]);
  useEffect(() => {
    fetchData();
  }, []);
  const fetchData = async () => {
    setCourses(await fetchCourses());
  };
  const fetchCourses = async () => {
    const data = await axios({
      method: "GET",
      url: url,
      headers: {
        Public: 'public',
      },
    })
      .then((response) => {
        setLoading(false);

        return response.data;
      })
      .catch((err) => {
        console.log(err, url);
      });
    return data;
  };
  return (
    <section className="best-seller">
      <div className="container-fluid">
        <div className="row">
          <div className="col-md-4 pb-4 pt-5">
            <div className="h-100 best-seller-sologan">
              <div className="px-5">
                <h5 className="font-weight-bold">
                  The world’s largest selection of courses
                </h5>
                <p>
                  Choose from over 100,000 online video courses with new
                  additions published every month.
                </p>
              </div>
            </div>
          </div>
          <div className="col-md-8">
            <h3 className="title">{title}</h3>
            {loading && <SkeletonLoader width={window.innerWidth / 2} />}

            <div className="row">
              {courses &&
                courses.map((course) => (
                  <div key={course.id} className="col-md-3">
                    <div className="course">
                      <img src={course.image} />
                      <h6 className="course-title">{course.title}</h6>
                      <p class="font-italic text-center">
                        <i className="fa fa-user"></i>
                        {" " + course.students + " students"}
                      </p>
                      <small className="course-content">{course.content}</small>

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
                      {course.price !=
                        0 && (
                          <div className="seller-label">
                            {course.discount + "%"}
                          </div>
                        )}
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
                              <i className="fa fa-play-circle" />{" "}
                              {course.lecturerCount + " lectures"}
                            </span>
                            <span className="mx-1"> | </span>
                            <span>
                              <i className="fa fa-clock-o" />{" "}
                              {course.lengthVideos + " hours"}
                            </span>
                          </div>
                          <small className="course-content">
                            {course.content}
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
          </div>
        </div>
      </div>
    </section>
  );
}
export default SingleCourseWithBannerSection;
