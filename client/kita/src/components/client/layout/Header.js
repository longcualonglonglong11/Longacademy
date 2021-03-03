import { useEffect, useRef, useState } from "react";
import axios from "axios";
import SkeletonLoader from "../../fragment/SkeletonLoader";
import logoBanner from "../../../assets/client/img/logo.png";
import { Link } from "react-router-dom";
import {
  CLIENT_URL,
  HOMEPAGE_URL,
  LOGIN_URL,
  CART_URL,
} from "../../../utility/UrlConstant";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchCategories,
  selectAllCategory,
} from "../../../features/category/categorySlice";
function Header() {
  // const [categories, setCategories] = useState([]);
  const user = useSelector((state) => state.user);
  const categories = useSelector((state) => {
    const data = selectAllCategory(state);
    const status = state.categories.status;
    return { data, status };
  });
  const dropdownMenuRef = useRef();
  const dropdownRef = useRef();
  useEffect(() => {
    setUpDropdowmMenu();
    fetchData();
  }, []);
  const fetchCategory = async () => {
    const data = await axios({
      method: "GET",
      url: "http://localhost:8080/api/category",
      // headers: {
      //   Authorization: `Bearer 123`,
      // },
      // headers: {
      //   Public: "nani",
      // },
    })
      .then((response) => {
        return response.data;
      })
      .catch((err) => {
        console.log(err);
      });
    return data;
  };
  const accBtnStyle = {
    width: "70%",
    borderRadius: 25
  };
  const dispatch = useDispatch();
  const fetchData = async () => {
    dispatch(fetchCategories());
    // setCategories(await fetchCategory());
  };
  const setUpDropdowmMenu = () => {
    const dropdownMenu = dropdownMenuRef.current;
    const dropdown = dropdownRef.current;
    const dropdownClone = document.querySelector(".dropdown");
    dropdown.style.width = String(dropdownClone.offsetWidth) + "px";
    dropdown.style.height = String(dropdownClone.offsetHeight + 150) + "px";
    dropdown.style.position = "absolute";
    dropdown.addEventListener("mouseover", () => {
      dropdownMenu.classList.add("show");
    });
    const delay = (ms) => new Promise((res) => setTimeout(res, ms));

    //For ensure that the div will be shown whatever mouse moving
    dropdown.addEventListener("mouseout", async () => {
      dropdownMenu.classList.remove("show");
    });
    dropdownClone.addEventListener("mouseover", () => {
      dropdownMenu.classList.add("show");
    });
    // const delay = (ms) => new Promise((res) => setTimeout(res, ms));

    dropdownClone.addEventListener("mouseout", async () => {
      dropdownMenu.classList.remove("show");
    });
  };
  return (
    <div className="Header">
      <header className="container-fluid border-bottom">
        <nav className="navbar navbar-expand-sm navbar-light bg-light">
          <Link to={CLIENT_URL + HOMEPAGE_URL} className="navbar-brand"></Link>
          <button
            className="navbar-toggler d-lg-none"
            type="button"
            data-toggle="collapse"
            data-target="#collapsibleNavId"
            aria-controls="collapsibleNavId"
            aria-expanded="false"
            aria-label="Toggle navigation"
          />
          <div className="collapse navbar-collapse row" id="collapsibleNavId">
            <div className="col-md-6">
              <ul className="navbar-nav mr-auto mt-2 mt-lg-0 d-flex align-items-center justify-content-between">
                <li className="nav-item mr-3">
                  <div ref={dropdownRef} className="dropdown-expand"></div>
                  <div className="dropdown">
                    <a
                      className="btn btn-outline-light dropdown-toggle"
                      data-toggle="dropdown"
                    >
                      <i className="fa fa-th" />
                      <span className="ml-2">Categories</span>
                    </a>

                    <div
                      ref={dropdownMenuRef}
                      style={{
                        zIndex: "9999",
                      }}
                      className="dropdown-menu"
                    >
                      {categories && categories.status === "loading" && (
                        <SkeletonLoader />
                      )}
                      {categories &&
                        categories.data &&
                        categories.data.map((category) => (
                          <Link
                            to={CLIENT_URL + "/category/" + category.id}
                            key={category.id}
                            className="dropdown-item"
                          >
                            <i class={category.icon} />
                            <span className="category-txt">
                              {category.title}
                            </span>
                          </Link>
                        ))}
                    </div>
                  </div>
                </li>
                <li className="nav-item w-100">
                  <form
                    className="input-group nav-search"
                    id="search-course"
                    // action="@{${(T(com.myclass.util.UrlConstants.Client).COURSE_SEARCH)}}"
                    method="get"
                  >
                    <input
                      type="text"
                      id="title-course"
                      className="form-control"
                      placeholder="Search courses"
                    />
                    <div className="input-group-append">
                      <button
                        type="submit"
                        className="btn bg-white text-danger"
                        onsearch="SearchCourse()"
                        id="btn-search"
                      >
                        <i className="fa fa-search" />
                      </button>
                    </div>
                  </form>
                </li>
              </ul>
            </div>

            <div className="col-md-1 nav-cart">
              {user && user.data && user.data.courseInCart && (
                <Link to={CLIENT_URL + CART_URL}>
                  <i className="fa fa-shopping-basket" />
                  {" " + user.data.courseInCart.length}
                </Link>
              )}{" "}
            </div>

            {user.data && !user.data.email ? (
              <div className="col-md-5 text-right">
                <Link
                  to={LOGIN_URL}
                  className="btn btn-md btn-outline-secondary"
                >
                  Login
                </Link>
                <Link className="btn btn-md btn-danger ml-1  text-white">
                  Sign up
                </Link>
              </div>
            ) : (
            <div className="col-md-2 ml-auto" ><img style={accBtnStyle} height="70px"  src={user.data.avatar}/></div>
            )}
          </div>
        </nav>
      </header>
    </div>
  );
}

export default Header;
