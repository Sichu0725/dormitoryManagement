import MainHeader from './header'
import { Content } from '@carbon/react'

const Layout = ({ children, logout }) => {
  return (
    <>
      <MainHeader logout={logout} />
      <Content children={children} />
    </>
  )
}

export default Layout
