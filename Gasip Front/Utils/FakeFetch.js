const HOST = "https://dummyjson.com";

async function fakeBoardsFetch () {
    const res = await fetch(HOST + "/posts");
    const data = await res.json();
    const mod_data = await data.posts.map((item) => {
        return {
            postId: item.id,
            title: item.title,
            content: item.body,
            likeCount: item.reactions,
        }
    })
    return mod_data;
}

export {fakeBoardsFetch};