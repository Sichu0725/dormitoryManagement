import React from 'react'
import {
  Header,
  HeaderContainer,
  HeaderName,
  HeaderNavigation,
  HeaderMenuItem,
  // HeaderGlobalBar,
  // HeaderGlobalAction,
  SideNav,
  SideNavItems,
  SideNavLink,
  SkipToContent,
  HeaderMenuButton,
  SideNavDivider,
} from '@carbon/react'
import {
  Notification,
  Home,
  // Login,
  // Catalog,
  // User,
  Building,
  Logout,
} from '@carbon/icons-react'
import { Link } from 'react-router-dom'

const MainHeader = ({ logout }) => (
  <HeaderContainer
    render={({ isSideNavExpanded, onClickSideNavExpand }) => (
      <Header aria-label="GBSW 기숙사 관리 프로그램">
        <SkipToContent />
        <HeaderMenuButton
          aria-label="Open menu"
          isCollapsible
          onClick={onClickSideNavExpand}
          isActive={isSideNavExpanded}
        />
        <HeaderName prefix="GBSW" element={Link} to="/">
          기숙사 학생 관리 프로그램
        </HeaderName>

        <HeaderNavigation aria-label="GBSW">
          <HeaderMenuItem element={Link} to="/">
            학생 전체 보기
          </HeaderMenuItem>
          {/* <HeaderMenuItem element={Link} to="/dormitory-log">
            학생 입출입 기록
          </HeaderMenuItem> */}
          <HeaderMenuItem element={Link} to="/notification">
            기숙사 공지
          </HeaderMenuItem>
        </HeaderNavigation>

        {/* <HeaderGlobalBar>
          <HeaderGlobalAction aria-label="로그인" onClick={() => {}}>
            <Login />
          </HeaderGlobalAction>
        </HeaderGlobalBar> */}

        <SideNav
          aria-label="Side navigation"
          isRail
          expanded={isSideNavExpanded}
          onOverlayClick={onClickSideNavExpand}
        >
          <SideNavItems>
            <SideNavLink element={Link} to="/" renderIcon={Home}>
              메인
            </SideNavLink>
            <SideNavDivider />
            <SideNavLink element={Link} to="/" renderIcon={Building}>
              학생 전체 보기
            </SideNavLink>
            {/* <SideNavLink
              element={Link}
              to="/dormitory-log"
              renderIcon={Catalog}
            >
              학생 입출입 기록
            </SideNavLink> */}
            <SideNavLink
              element={Link}
              to="/notification"
              renderIcon={Notification}
            >
              기숙사 공지
            </SideNavLink>
            {/* <SideNavLink element={Link} to="/notification" renderIcon={User}>
              마이페이지
            </SideNavLink> */}
            <SideNavDivider />
            <SideNavLink onClick={logout} renderIcon={Logout}>
              로그아웃
            </SideNavLink>
          </SideNavItems>
        </SideNav>
      </Header>
    )}
  />
)

export default React.memo(MainHeader)
