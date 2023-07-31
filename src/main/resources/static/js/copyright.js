function getDate() {
    const currentYear = new Date(Date.now());
    return currentYear.getFullYear();
}
export default getDate;