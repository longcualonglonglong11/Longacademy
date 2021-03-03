import { useEffect, useRef } from "react";
import "../../../assets/auth/vendor/bootstrap/css/bootstrap.min.css";
import "../../../assets/auth/fonts/font-awesome-4.7.0/css/font-awesome.min.css";
import "../../../assets/auth/vendor/animate/animate.css";

import "../../../assets/auth/vendor/css-hamburgers/hamburgers.min.css";
import "../../../assets/auth/vendor/select2/select2.min.css";

import "../../../assets/auth/css/util.css";

import "../../../assets/auth/css/main.css";

import loginImg from "../../../assets/auth/images/img-01.png";
import { Link, Redirect } from "react-router-dom";
import ScriptTag from "react-script-tag/lib/ScriptTag";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { fetchUsers } from "../../../features/user/userSlice";

function LogInForm({ match }) {
  const emailRef = useRef();
  const passwordRef = useRef();
  const dispatch = useDispatch()
  useEffect(() => {}, []);
  const user = useSelector(state=> state.user)
  const login = async () => {
    const data = {
      email: emailRef.current.value,
      password: passwordRef.current.value,
    };
    dispatch(fetchUsers(data));
    
  };
  const RedirectAccount = () => {
    let url = "";
    if(user.roleName == '')
      url=""
    else
      url="/client/home"
    return (<Redirect to={url}></Redirect>)
  }
  return (
    <div className="LogInForm">
      <div>
        <button onClick={ () =>
          console.log(user)
        }>AA</button>
        <meta charSet="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
      {user && user.status === 'completed' &&  <RedirectAccount/>}
        <div className="limiter">
          <div className="container-login100">
            <div className="wrap-login100">
              <div className="login100-pic js-tilt" data-tilt>
                <img src={loginImg} alt="IMG" />
              </div>
              <form className="login100-form validate-form">
                <span className="login100-form-title">Member Login</span>
                <div
                  className="wrap-input100 validate-input"
                  data-validate="Valid email is required: ex@abc.xyz"
                >
                  <input
                    ref={emailRef}
                    className="input100"
                    type="text"
                    name="email"
                    placeholder="Email"
                  />
                  <span className="focus-input100" />
                  <span className="symbol-input100">
                    <i className="fa fa-envelope" aria-hidden="true" />
                  </span>
                </div>
                <div
                  className="wrap-input100 validate-input"
                  data-validate="Password is required"
                >
                  <input
                    ref={passwordRef}
                    className="input100"
                    type="password"
                    name="pass"
                    placeholder="Password"
                  />
                  <span className="focus-input100" />
                  <span className="symbol-input100">
                    <i className="fa fa-lock" aria-hidden="true" />
                  </span>
                </div>
                <div className="container-login100-form-btn">
                  <div onClick={() => login()} className="login100-form-btn">Login</div>
                </div>
                <div className="text-center p-t-12">
                  <span className="txt1">Forgot</span>
                  <Link className="txt2"> Username / Password?</Link>
                </div>
                <div className="text-center p-t-136">
                  <a className="txt2" href="#">
                    Create your Account
                    <i
                      className="fa fa-long-arrow-right m-l-5"
                      aria-hidden="true"
                    />
                  </a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <ScriptTag
        src={"../../../assets/auth/vendor/jquery/jquery-3.2.1.min.js"}
      />
      <ScriptTag src={"../../../assets/auth/vendor/bootstrap/js/popper.js"} />
      <ScriptTag
        src={"../../../assets/auth/vendor/bootstrap/js/bootstrap.min.js"}
      />

      <ScriptTag src={"../../../assets/auth/vendor/select2/select2.min.js"} />
      <ScriptTag src={"../../../assets/auth/vendor/tilt/tilt.jquery.min.js"} />

      <ScriptTag src={"../../../assets/auth/js/main.js"} />
    </div>
  );
}

export default LogInForm;
