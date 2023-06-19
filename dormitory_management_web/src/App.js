import { Route, Routes } from 'react-router-dom'
import { Theme } from '@carbon/react'
import Layout from './components/layout'
import Home from './pages/home'
import Login from './pages/login'
import { useEffect } from 'react'
import DormitoryLog from './pages/dormitoryLog'
import Notification from './pages/notification'
import AllStudents from './pages/allStudents'
import { useCallback } from 'react'
import './style/global.scss'
import axios from 'axios'
import AddNotification from './pages/addNotification'
import NotificationContents from './pages/notificationContents'

const App = () => {
  useEffect(() => {
    axios.defaults.withCredentials = true

    window
      .matchMedia('(prefers-color-scheme: dark)')
      .addEventListener('change', (e) =>
        onSelectMode(e.matches ? 'dark' : 'light')
      )

    onSelectMode(
      window.matchMedia('(prefers-color-scheme: dark)').matches
        ? 'dark'
        : 'light'
    )

    return () => {
      window
        .matchMedia('(prefers-color-scheme: dark)')
        .removeEventListener('change', () => {})
    }
  }, [])

  const onSelectMode = (theme) => {
    if (theme === 'dark') {
      window.document.getElementById('theme').className =
        'cds--g90 cds--layer-one '
    } else {
      window.document.getElementById('theme').className =
        'cds--white cds--layer-one '
    }
  }

  const logout = useCallback(() => {
    window.localStorage.removeItem('token')
    window.location.href = '/'
  }, [])

  if (localStorage.getItem('token') === null) return <Login />

  return (
    <Theme id="theme">
      <Layout logout={logout}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/dormitory-log" element={<DormitoryLog />} />
          <Route path="/notification" element={<Notification />} />
          <Route path="/all-students" element={<AllStudents />} />
          <Route path="/add-notification" element={<AddNotification />} />
          <Route
            path="/notification-contents"
            element={<NotificationContents />}
          />
        </Routes>
      </Layout>
    </Theme>
  )
}

export default App
