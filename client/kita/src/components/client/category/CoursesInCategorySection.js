import { useSelector } from "react-redux";
import { selectCategoryById } from "../../../features/category/categorySlice";
import SingleCourseWithBannerSection from "../course/SingleCourseWithBannerSection";
import SingleCourseSection from "../course/SingleCourseSection";

function CoursesInCategorySection({ match }) {
  const { categoryId } = match.params;

  const category = useSelector((state) =>
    selectCategoryById(state, categoryId)
  );  return <div classname="CoursesInCategorySection">
    <h1 className="text-center mt-4">Search in category: {category.title.toUpperCase()}</h1>
    <SingleCourseSection icon={category.icon} title={category.title} data={category.courseDtos}></SingleCourseSection>
  </div>;
}

export default CoursesInCategorySection;
