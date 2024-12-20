
export default interface IToken {
    
    token: string
    type: string
    expiresIn: number
    id: number
    name: string
    roles: string[]
}