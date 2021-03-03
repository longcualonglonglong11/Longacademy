import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "../../../assets/client/css/home.css";
import { CLIENT_URL } from "../../../utility/UrlConstant";
import SkeletonLoader from "../../fragment/SkeletonLoader";

function CategorySection({ url }) {
  const [categories, setCategories] = useState([]);

  const [loading, setLoading] = useState(true);
  useEffect(() => {
    fetchData();
  }, []);
  const fetchCategory = async () => {
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
        console.log(err);
      });
    return data;
  };
  const fetchData = async () => {
    setCategories(await fetchCategory());
  };

  return (
    <div classname="CategorySection">
      <section className="categories">
        <div className="container-fluid">
          <h3 className="title mt-0">Top Categories</h3>
          <div
            //   style={{
            //             justifyContent: 'center'
            //         }}
            className="row"
          >
            {categories &&
              categories.length > 0 &&
              categories.map((category) => (
                <div className="col-md-3">
                  <Link
                    to={CLIENT_URL + "/category/" + category.id}
                    className="category text-decoration-none"
                  >
                    <i
                      style={{
                        color: "#000000",
                        fontWeight: 500,
                      }}
                      class={category.icon}
                    />
                    <span
                      style={{
                        color: "#000000",
                        fontWeight: 1000,
                      }}
                    >
                      {category.title}
                    </span>

                    <span className="ml-auto">
                      <i
                        style={{
                          fontSize: 17,
                          color: "#00b3ff",
                        }}
                        class="fa fa-user mr-1"
                      />
                      {category.students}
                    </span>
                  </Link>{" "}
                </div>
              ))}
          </div>
        </div>
      </section>
      {loading && <SkeletonLoader width={window.innerWidth} />}
    </div>
  );
}

export default CategorySection;
