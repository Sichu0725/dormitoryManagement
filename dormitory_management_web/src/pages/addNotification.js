import { Button, Form, Stack, TextArea, TextInput } from '@carbon/react'
import { useState } from 'react'
import { ToastContainer, toast } from 'react-toastify'

const AddNotification = () => {
  const [theme, setTheme] = useState('light')
  const [notification, setNotification] = useState({
    title: '',
    contents: '',
  })

  const onchageTitle = (e) =>
    setNotification({
      ...notification,
      title: e.target.value,
    })

  const onChangeContents = (e) =>
    setNotification({
      ...notification,
      contents: e.target.value,
    })

  const fetchData = async () => {
    if (notification.contents === '' || notification.title === '') {
      showToast('warn', '빈칸이 있습니다.')
      return
    }

    const data = await fetch(
      'http://localhost:8080/api/v1/notification/add-notification',
      {
        method: 'POST',
        body: JSON.stringify({
          title: notification.title,
          contents: notification.contents,
        }),
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + localStorage.getItem('token'),
        },
      }
    )
      .then((res) => res.json())
      .catch((err) => {
        showToast('warn', '관리자만 공지를 게시할 수 있습니다.')
      })

    if (data.status === 200) {
      showToast('success', '공지 게시가 완료되었습니다.')
      window.location.href = '/'
    } else {
      // id , pw 틀림
      showToast('warn', '공지 게시에 실패하였습니다.')
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
    <div className="add-notification container">
      <h1 className="con-title">공지글 작성</h1>
      <Form>
        <Stack gap={4}>
          <TextInput
            labelText="공지의 제목을 입력하세요."
            type="text"
            size="md"
            onChange={(e) => onchageTitle(e)}
          />
          <TextArea
            cols={250}
            rows={10}
            invalid={false}
            disabled={false}
            labelText="공지 내용을 작성해주세요."
            onChange={(e) => onChangeContents(e)}
          />

          <Button onClick={() => fetchData()}>공지 게시</Button>
        </Stack>
      </Form>

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
    </div>
  )
}

export default AddNotification
