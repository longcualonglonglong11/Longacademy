
import { Switch, Route } from "react-router-dom";
import Style from '../layout/Style'
import Script from '../layout/Script'
import Header from '../layout/Header'
import Footer from '../layout/Footer'

import { CART_URL, CATEGORY_URL, COURSE_DETAIL_URL, HOMEPAGE_URL, LOGIN_URL } from "../../../utility/UrlConstant";
import Home from "../layout/Home";
import CourseDetailPage from "../course/CourseDetailPage";
import CoursesInCategorySection from "../category/CoursesInCategorySection";
import { useSelector } from "react-redux";
import SingleCourseSection from "../course/SingleCourseSection";
const CourseInCart = () => {
  const courses = useSelector(state => state.user.data.courseInCart);
  console.log(courses)
  return(
    <SingleCourseSection icon={"fa fa-shopping-basket"} title={" YOUR CART"} data={courses}/>
  )
}
const BuyedCourses = () => {
  const courses = useSelector(state => state.user.data.buyedCourse);
  console.log(courses)
  return(
    <SingleCourseSection icon={"fa fa-shopping-basket"} title={" YOUR COURSES"} data={courses}/>
  )
}

function ClientPage({ match }) {
  return (
    <div className="ClientPage">
      <Style />
      <Header/>
      <Switch>

        <Route path={match.url + HOMEPAGE_URL} component={Home} />
        <Route path={match.url + COURSE_DETAIL_URL + '/:courseId'} component={CourseDetailPage} />
        <Route path={match.url + CATEGORY_URL + '/:categoryId'} component={CoursesInCategorySection} />
        <Route path={match.url + CART_URL} component={() => <CourseInCart/>} />
        {/* <Route path={match.url + CART_URL} component={() => <BuyedCourses/>} /> */}

      </Switch>
      <Footer />
      <Script />
    </div>
  );
}

export default ClientPage;
