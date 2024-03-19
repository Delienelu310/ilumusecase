import { useRef, useEffect } from "react";

export default function TableCanvas({table}){
    
    const canvasRef = useRef(null);

    

    useEffect(() => {
      const canvas = canvasRef.current;
      const ctx = canvas.getContext('2d');
  
      // Draw green oval with brown border
      ctx.beginPath();
      ctx.fillStyle = 'green';
      ctx.strokeStyle = 'brown';
      ctx.lineWidth = 2;
      ctx.ellipse(canvas.width / 2, canvas.height / 2, 150, 100, 0, 0, Math.PI * 2);
      ctx.fill();
      ctx.stroke();
  
      // Draw 6 white rectangles along the perimeter
      const rectangleWidth = 40;
      const rectangleHeight = 20;
      const padding = 10;
      const totalRectangles = 6;
      const angleIncrement = (Math.PI * 2) / totalRectangles;
  
      for (let i = 0; i < totalRectangles; i++) {
        const angle = i * angleIncrement;
        const x = (canvas.width / 2) + Math.cos(angle) * (150 + padding) - rectangleWidth / 2;
        const y = (canvas.height / 2) + Math.sin(angle) * (100 + padding) - rectangleHeight / 2;
  
        ctx.fillStyle = 'white';
        ctx.fillRect(x, y, rectangleWidth, rectangleHeight);
      }
    }, []);
    
    
    return (
        <div>

        </div>
    );
}