import logo from '../assets/symbol-solid.png'

const Header = ({ isLoggedIn }) => {
  return (
    <div className="header">
      <div>
        <img src={logo} alt="gbsw_logo" />
        <h1>
          <span>GBSW</span>
          <br />
          기숙사 학생 관리 프로그램
        </h1>
      </div>

      <ul>
        <li>메인</li>
        {/* <li>검색</li> */}
        <li>공지</li>
        {isLoggedIn ? <li>로그인</li> : <li>로그아웃</li>}
      </ul>
    </div>
  )
}

export default Header
