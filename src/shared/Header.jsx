import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import symbol from "../img/symbol.png";

const Header = () => {
  const navigate = useNavigate();

  return (
    <Wrapper>
      <Logo onClick={() => navigate("/")}>
        <LogoImg src={symbol} />
        <LogoTxt>CUKLION EDU</LogoTxt>
      </Logo>

      <ButtonWrapper>
        <Button onClick={() => navigate("/login")}>로그인</Button>
        <Button onClick={() => navigate("/register")}>회원가입</Button>
      </ButtonWrapper>
    </Wrapper>
  );
};

const Wrapper = styled.header`
  background-color: #fcfbfb;
  //border: 1px solid black;
  width: 100%;
  height: 35px;
  display: flex;
  align-items: center;
  padding: 0 20px;
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

const ButtonWrapper = styled.div`
  margin-left: auto;
`;

const Button = styled.button`
  border: none;
  background-color: transparent;
  margin-left: 10px;
`;
export default Header;
