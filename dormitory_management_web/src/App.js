import { Route, Routes } from 'react-router-dom'
import Home from './pages/home'

import './style/global.scss'
import Layout from './components/layout'
import Login from './pages/login'

const App = () => {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </Layout>
  )
}

export default App
