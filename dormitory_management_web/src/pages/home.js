import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@carbon/react'
import { useEffect, useRef, useState } from 'react'
const Home = () => {
  const [data, setData] = useState({
    state: '',
    data: [],
  })
  const headers = ['학번', '이름', '성별', '호실', '기숙사 입소 여부']

  const fetchData = async () => {
    const res = await fetch('http://localhost:8080/api/v1/user/all-student')
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
    console.log(1)
  }, [])

  return (
    <div className="container">
      <h1 className="con-title">기숙사 학생 입소/외출 현황</h1>
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
            data.data.map((item) => {
              if (item['stu_code'] !== 0)
                return (
                  <TableRow>
                    {Object.keys(item)
                      .filter(
                        (key) =>
                          key === 'stu_code' ||
                          key === 'name' ||
                          key === 'gender' ||
                          key === 'room' ||
                          key === '_in'
                      )
                      .map((key) => {
                        if (key === '_in') {
                          if (item[key])
                            return <TableCell key={key}>입소 중</TableCell>
                          else return <TableCell key={key}>외출 중</TableCell>
                        }
                        if (key === 'gender') {
                          if (item[key] === 0)
                            return <TableCell key={key}>남자</TableCell>
                          else return <TableCell key={key}>여자</TableCell>
                        }
                        return <TableCell key={key}>{item[key]}</TableCell>
                      })}
                  </TableRow>
                )
            })}
        </TableBody>
      </Table>
    </div>
  )
}

export default Home
