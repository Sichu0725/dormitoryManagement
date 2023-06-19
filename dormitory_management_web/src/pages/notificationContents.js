import QueryString from 'qs'
import { useEffect, useState } from 'react'

const NotificationContents = () => {
  const [data, setData] = useState({
    status: '',
    data: {
      title: '',
      contents: '',
      createdAt: '',
    },
  })
  const fetchData = async (idx) => {
    const res = await fetch(
      'http://localhost:8080/api/v1/notification/get-notification?idx=' + idx
    )
      .then((res) => res.json())
      .then((res) => setData(res))
  }
  useEffect(() => {
    const queryData = QueryString.parse(window.location.search, {
      ignoreQueryPrefix: true,
    })
    fetchData(queryData.idx)
    console.log(data)
  }, [])
  return (
    <div className="container">
      <h1>{data.data.title}</h1>
      <hr />

      <p>{data.data.createdAt}</p>
      <br />
      <p>{data.data.contents}</p>
    </div>
  )
}

export default NotificationContents
