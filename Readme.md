# Sudoku Game Grid🎮
- added `classes` and `workflow for packaging`
- added `dockers`
- added The `Game Engine`

## Objectives🎯
- `9 x 9` grid with `Easy` `Medium` and `Hard` Level
- `X x Y` grid 

### Author 👨‍💻 and Certifications 📜
- Zin Lin Htun
- Project Status ![GitHub Workflow Status Master](https://img.shields.io/github/actions/workflow/status/zin-lin/Sudoku/main.yml)
- [![LICENSE](https://img.shields.io/github/license/zin-lin/Sudoku.svg?style=flat-square)](https://github.com/zin-lin/Sudoku/blob/master/LICENSE)
- <b style='color:red'>`WARNING`</b>- If any unauthorised use is made, there will be academics consequences

### Tool Chain 🔨 
- `Java` ☕
- `Maven`
- `IntelliJ`📱
- `Dockerfile` 🐳
- Preview Gameplay
- ![alt text](./3x3.png)
- Preview Help mode
- ![alt text](./7xh.png)
- - Preview irregular grids
- ![alt text](./7x7.png)
- ![alt text](./sxs.png)

### Developer Mode `--alpha`
available syntax and commands
- go into developer mode
```cmd
>> Current grid is: (1,3) use W,S,A,D to move around or type in values
>> dev 
```
- resetting game - remember the game has to be valid, author's application does not check this.
```powershell
>> cmd <setGame> 0,0,0,4,9,6,0,0,0/7,0,3,0,0,0,0,9,0/0,0,0,0,0,0,5,0,0/0,8,4,7,0,0,0,0,0/0,0,0,0,0,0,0,5,2/3,0,0,1,0,0,7,0,0/0,4,0,0,5,0,0,2,7/6,0,0,8,0,9,0,0,3/0,0,0,0,0,4,0,0,0/
```
- printing the answer 
```powershell
>> cmd <printAnswer>
```
- winning the game automatically `--solving`
```powershell
>> cmd <solveGame>
```
or
```powershell
>> cmd <solve>
```
- getting out of developer mode
```powershell
>> cmd <player>
```
or
```powershell
>> cmd <quit>
```


### ChangeLog 💻
- `Interactive Console` is now working - `(15:28 2023/02/10)`
- `@Deprecated` - `SudokuGenerator` and `Easy Sudoku Class` - `(18:00 2023/02/09)` 
- Added `SudokuGenarator` and `Helper` classes to check grid- `(19:10 2023/02/01)`
- Added `Easy Sudoku Class` - `(17:22 2023/02/01)`
- Added `Separator` - `(16:36 2023/02/01)`
- Added Multi-dimensional reshape Support - `(16:31 2023/02/01)`
- Added `Dockerfile` - `(23:50 2023/01/31)`
- Fixed Bugs with `UTF-8` - `(21:31 2023/01/31)`
  - Do use 
    ```powershell
     Ctrl + F 5 //IntelliJ
    ```
  - Build IntelliJ
    - Use CMD to execute with maximum efficiency.
    - ```powershell
      PS> java com.napier.sudoku.GameEngine
      ```
