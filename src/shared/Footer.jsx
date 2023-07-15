import styled from "styled-components";

const Footer = () => {
  return (
    <Wrapper>
      <Contents>
        <FooterTxt>Copyright ⓒ 2023 멋쟁이사자처럼 가톨릭대학교</FooterTxt>
        <Navigation>
          <ul>
            <li>
              <Button href="https://github.com/CUK-LikeLion-Common/Evaluation-Lecture-team10">
                깃허브
              </Button>
            </li>
            <li>
              <Button href="https://www.instagram.com/likelioncuk/?igshid=MmIzYWVlNDQ5Yg%3D%3D">
                인스타그램
              </Button>
            </li>
          </ul>
        </Navigation>
      </Contents>
    </Wrapper>
  );
};

const Wrapper = styled.footer`
  background-color: #011445;
  color: white;
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

const FooterTxt = styled.div`
  color: #ffffff;
  display: flex;
  align-items: center;
  border: none;
  background-color: transparent;
  padding: 0;
  margin-right: auto;
  cursor: pointer;
  font-size: 12px;
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

const Button = styled.a`
  border: none;
  background-color: transparent;
  margin-left: 10px;
  color: #ffffff;
  font-size: 12px;
  text-decoration: none;
`;

export default Footer;
