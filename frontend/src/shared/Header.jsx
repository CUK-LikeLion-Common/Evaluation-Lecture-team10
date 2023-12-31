import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import symbol from "../img/symbol.png";
import axios from "axios";

function Header() {
  const navigate = useNavigate();
  const user_id = sessionStorage.getItem("user_id")
    ? sessionStorage.getItem("user_id")
    : null;
  const user_password = sessionStorage.getItem("user_password")
    ? sessionStorage.getItem("user_password")
    : null;

  const requestData = {
    userID: user_id,
    userPassword: user_password,
  };

  const onClickLogout = () => {
    axios
      .post("/logout", requestData)
      .then((response) => {
        sessionStorage.removeItem("user_id", requestData.userID);
        sessionStorage.removeItem("user_password", requestData.userPassword);
        alert("로그아웃 되었습니다.");

        navigate("/");
      })
      .catch((error) => {
        console.error(error);
        alert("로그인 되어있지 않습니다.");
      });
  };

  const onClickWithDraw = () => {
    const confirmed = window.confirm("정말로 탈퇴하시겠습니까?");
    if (confirmed) {
      axios
        .delete(`/delete/${user_id}`, {
          data: {
            userID: user_id,
            userPassword: user_password,
          },
        })
        .then((response) => {
          console.log(response);
          navigate("/");
          alert("회원탈퇴 되었습니다.");
          sessionStorage.removeItem("user_id", requestData.userID);
          sessionStorage.removeItem("user_password", requestData.userPassword);
        })
        .catch((error) => {
          console.error(error);
          alert("회원탈퇴에 실패했습니다.");
        });
    }
  };

  return (
    <Wrapper>
      <Contents>
        <Logo onClick={() => navigate("/")}>
          <LogoImg src={symbol} />
          <LogoTxt>CUKLION EDU</LogoTxt>
        </Logo>

        <Navigation>
          <ul>
            {user_id ? (
              <>
                <li>{user_id} 환영합니다!</li>
                <li>
                  <Button onClick={() => navigate("/write")}>글쓰기</Button>
                </li>
                <li>
                  <Button onClick={onClickLogout}>로그아웃</Button>
                </li>

                <li>
                  <Button onClick={onClickWithDraw}>회원탈퇴</Button>
                </li>
              </>
            ) : (
              <>
                <li>
                  <Button onClick={() => navigate("/login")}>로그인</Button>
                </li>
                <li>
                  <Button onClick={() => navigate("/register")}>
                    회원가입
                  </Button>
                </li>
              </>
            )}
          </ul>
        </Navigation>
      </Contents>
    </Wrapper>
  );
}

const Wrapper = styled.header`
  background-color: #fcfbfb;
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 50px;
`;

const Contents = styled.div`
  display: flex;
  width: 96%;
  max-width: 1200px;
  height: 100%;
  margin: 0 auto;
  align-items: center;
  justify-content: space-between;
`;

const Logo = styled.div`
  display: flex;
  align-items: center;
  border: none;
  background-color: transparent;
  padding: 0;
  margin-right: auto;
  cursor: pointer;
`;

const LogoImg = styled.img`
  width: 33px;
  margin-right: 15px;
`;

const LogoTxt = styled.div`
  font-size: 18px;
  font-weight: 600;
`;

const Navigation = styled.nav`
  ul {
    display: flex;
    list-style: none;

    li + li {
      margin-left: 30px;
    }
  }
`;

const Button = styled.button`
  border: none;
  background-color: transparent;
  margin-left: 10px;
`;
export default Header;
