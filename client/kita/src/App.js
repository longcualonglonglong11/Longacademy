import ClientPage from "./components/client/home/ClientPage";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import {
  CLIENT_URL,
  COURSE_DETAIL_URL,
  HOMEPAGE_URL,
  LOGIN_URL,
} from "./utility/UrlConstant";
import LogInForm from "./components/fragment/auth/LoginForm";
function App() {
  return (
    <div className="App">
      <Router>
        <Redirect to={CLIENT_URL + HOMEPAGE_URL}></Redirect>

        <Switch>
          <Route path={CLIENT_URL} component={ClientPage} />
          <Route path={LOGIN_URL} component={LogInForm} />

        </Switch>
      </Router>
    </div>
  );
}
export default App;
