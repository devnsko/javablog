export default interface IPost {
    id: number
    title: string
    content: string
    author_id: number
    author_name: string
    likes: ILikes[]
    createdAt: string
    updatedAt: string
}

export interface ILikes {
  id: number,
  blogId: number,
  userId: number,
  userName: string,
  likedAt: string
}