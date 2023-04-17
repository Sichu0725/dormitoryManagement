const Table = () => {
  return (
    <table className="main_table">
      <thead>
        <tr>
          <th>호실</th>
          <th>학번</th>
          <th>이름</th>
          <th>외출/잔류</th>
          <th>시간</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>205호</td>
          <td>3218</td>
          <td>최홍찬</td>
          <td>Out</td>
          <td>2023-03-21</td>
        </tr>
        <tr>
          <td>205호</td>
          <td>3218</td>
          <td>최홍찬</td>
          <td>In</td>
          <td>2023-03-21</td>
        </tr>
      </tbody>
    </table>
  )
}

export default Table
