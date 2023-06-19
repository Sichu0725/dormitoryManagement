import { Button, ButtonSet, Form, Stack, TextInput, Theme } from '@carbon/react'
import { useState } from 'react'
import { ToastContainer, toast } from 'react-toastify'
import style from '../style/login.module.scss'
import 'react-toastify/dist/ReactToastify.css'

const Login = () => {
  const [theme, setTheme] = useState('light')
  const [loginForm, setLoginForm] = useState({
    id: '',
    password: '',
  })

  const onchageId = (e) => {
    setLoginForm({
      ...loginForm,
      id: e.target.value,
    })
  }

  const onchagePw = (e) => {
    setLoginForm({
      ...loginForm,
      password: e.target.value,
    })
  }

  const fetchLogin = async () => {
    const data = await fetch('http://localhost:8080/api/v1/user/login', {
      method: 'POST',
      body: JSON.stringify({
        id: loginForm.id,
        password: loginForm.password,
      }),
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((res) => res.json())

    if (data.status === 200) {
      localStorage.setItem('token', data.data.accessToken)
      showToast('success', '로그인 성공')
      window.location.href = '/'
    } else {
      // id , pw 틀림
      showToast('warn', '아이디, 비밀번호를 확인해주세요.')
    }
  }

  const showToast = (type, msg) => {
    const theme =
      window.document.getElementById('theme').className ===
      'cds--g90 cds--layer-one '
        ? 'dark'
        : 'light'
    setTheme(theme)

    toast[type](msg, {
      position: 'top-left',
      autoClose: 2500,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: theme,
    })
  }

  return (
    <div className={style.container}>
      <Theme id="theme">
        <div className="login-form">
          <Form>
            <Stack gap={4}>
              <h1>로그인</h1>
              <TextInput
                labelText="ID를 입력하세요."
                type="text"
                size="md"
                onChange={(e) => onchageId(e)}
              />
              <TextInput
                labelText="PW를 입력하세요."
                type="password"
                size="md"
                onChange={(e) => onchagePw(e)}
              />
              <Stack gap={3}>
                <ButtonSet>
                  <Button
                    kind="primary"
                    disabled={false}
                    size="md"
                    type="button"
                    onClick={fetchLogin}
                  >
                    로그인
                  </Button>
                  <Button
                    kind="secondary"
                    disabled={false}
                    size="md"
                    type="reset"
                  >
                    취소
                  </Button>
                </ButtonSet>
                <p> 아이디/비밀번호 분실 시 관리자에게 문의해주세요.</p>
              </Stack>
            </Stack>
          </Form>
        </div>
        <ToastContainer
          position="top-left"
          autoClose={2500}
          limit={5}
          hideProgressBar={false}
          newestOnTop
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme={theme}
        />
      </Theme>
    </div>
  )
}

export default Login
