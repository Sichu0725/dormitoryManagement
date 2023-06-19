import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@carbon/react'
import { useEffect, useRef, useState } from 'react'

const Notification = () => {
  const [data, setData] = useState({
    state: '',
    data: [],
  })
  const headers = ['no', '제목', '작성일']

  const fetchData = async () => {
    const res = await fetch(
      'http://localhost:8080/api/v1/notification/all-list'
    )
      .then((res) => res.json())
      .then((res) => setData(res))
  }

  const useInterval = (callback, delay) => {
    const savedCallback = useRef(null)

    useEffect(() => {
      savedCallback.current = callback
    }, [callback])

    useEffect(() => {
      const executeCallback = () => {
        savedCallback.current()
        console.log(data)
      }

      const id = setInterval(executeCallback, delay)

      return () => clearInterval(id)
    }, [])
  }

  useInterval(fetchData, 5000)
  useEffect(() => {
    fetchData()
  }, [])

  return (
    <div className="container">
      <h1 className="con-title">기숙사 공지 목록</h1>
      <p style={{ color: 'gray' }}>공지 클릭시 상세보기로 이동합니다.</p>
      <Table size="lg" useZebraStyles={false}>
        <TableHead>
          <TableRow>
            {headers.map((header) => (
              <TableHeader id={header.key} key={header}>
                {header}
              </TableHeader>
            ))}
          </TableRow>
        </TableHead>
        <TableBody>
          {data.data &&
            data.data.map((item) => (
              <TableRow>
                {Object.keys(item)
                  .filter(
                    (key) =>
                      key === 'idx' || key === 'title' || key === 'createdAt'
                  )
                  .map((key) => {
                    if (key === 'createdAt') {
                      return (
                        <TableCell key={key}>
                          {item[key].substr(0, 10)}
                        </TableCell>
                      )
                    }
                    return (
                      <TableCell
                        onClick={() =>
                          (window.location.href = `/notification-contents?idx=${item['idx']}`)
                        }
                        key={key}
                      >
                        {item[key]}
                      </TableCell>
                    )
                  })}
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </div>
  )
}

export default Notification
