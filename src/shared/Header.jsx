import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import symbol from "../img/symbol.png";

const Header = () => {
  const navigate = useNavigate();

  return (
    <Wrapper>
      <Contents>
        <Logo onClick={() => navigate("/")}>
          <LogoImg src={symbol} />
          <LogoTxt>CUKLION EDU</LogoTxt>
        </Logo>

        <Navigation>
          <ul>
            <li>
              <Button onClick={() => navigate("/login")}>로그인</Button>
            </li>
            <li>
              <Button onClick={() => navigate("/register")}>회원가입</Button>
            </li>
          </ul>
        </Navigation>
      </Contents>
    </Wrapper>
  );
};

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
